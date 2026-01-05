package com.healthcare.uman.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.healthcare.uman.model.speciality.HumanPreferenceEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.healthcare.uman.dto.search.ResponseDTO;
import com.healthcare.uman.dto.search.SearchSessionDTO;
import com.healthcare.uman.dto.user.ProfessionalDTO;
import com.healthcare.uman.mapper.UserMapper;
import com.healthcare.uman.model.conversation.Conversation;
import com.healthcare.uman.model.conversation.ConversationState;
import com.healthcare.uman.model.conversation.Exchange;
import com.healthcare.uman.model.conversation.KeywordsResult;
import com.healthcare.uman.model.professional.HumanPreference;
import com.healthcare.uman.model.speciality.Speciality;
import com.healthcare.uman.model.user.Professional;
import com.healthcare.uman.model.user.User;
import com.healthcare.uman.repository.ConversationRepository;
import com.healthcare.uman.repository.HumanPreferencesRepository;
import com.healthcare.uman.repository.ProfessionalRepository;
import com.healthcare.uman.repository.SpecialityRepository;
import com.healthcare.uman.repository.UserRepository;
import com.healthcare.uman.utils.SearchUtils;

import opennlp.tools.tokenize.SimpleTokenizer;

@Service
public class SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    private final ProfessionalRepository professionalRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final SpecialityRepository specialityRepository;
    private final HumanPreferencesRepository humanPreferencesRepository;

    @Autowired
    public SearchService(ProfessionalRepository professionalRepository, UserRepository userRepository, ConversationRepository conversationRepository,
            SpecialityRepository specialityRepository, HumanPreferencesRepository humanPreferencesRepository) {
        this.professionalRepository = professionalRepository;
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
        this.specialityRepository = specialityRepository;
        this.humanPreferencesRepository = humanPreferencesRepository;
    }

    /**
     * Searches for professionals based on provided search session and conversation details.
     * It filters professionals by matching specialities and ranks them based on the number of matching human preferences.
     *
     * @param session The search session DTO containing search criteria.
     * @param conversation The conversation object containing keywords and human preferences.
     *
     * @return A list of professional DTOs matching the search criteria.
     */
    public List<ProfessionalDTO> performSearchProfessional(SearchSessionDTO session, Conversation conversation) {
        LOGGER.info("Performing professional search for session: {}", session.getSessionId());

        // Find specialities matching the keywords in the conversation.
        LOGGER.debug("Finding specialities for keywords: {}", conversation.getNonKeywords());
        System.out.println(conversation.getKeywords());
        List<String> keys = new ArrayList<>();
        keys.add("fatigue");
        List<Speciality> specialities = specialityRepository.findByKeywords(conversation.getKeywords());
        List<String> specialityIds = specialities.stream()
                                                 .filter(speciality -> conversation.getKeywords()
                                                                                   .stream()
                                                                                   .filter(keyword -> speciality.getKeywords().contains(keyword))
                                                                                   .count() >= 3)
                                                 .map(Speciality::getId)
                                                 .collect(Collectors.toList());

        // Find human preferences mentioned in the conversation.
        List<HumanPreference> humanPreferences = humanPreferencesRepository.findByNames(conversation.getHumanPreferences());
        List<String> humanPreferenceIds = humanPreferences.stream().map(HumanPreference::getId).toList();

        // Find professionals matching the identified speciality IDs.
        LOGGER.debug("Finding professionals for speciality IDs: {}", specialityIds);
        List<Professional> professionals = professionalRepository.findBySpecialityIds(specialityIds);

        // Sort professionals by the number of matching human preferences.
        List<Professional> sortedProfessionals = professionals.stream().sorted((p1, p2) -> {
            long count1 = p1.getProfessionalCard().getHumanPreferences().stream().filter(hp -> humanPreferenceIds.contains(hp.getId())).count();
            long count2 = p2.getProfessionalCard().getHumanPreferences().stream().filter(hp -> humanPreferenceIds.contains(hp.getId())).count();
            return Long.compare(count2, count1);
        }).collect(Collectors.toList());

        // Map sorted professionals to ProfessionalDTOs.
        return UserMapper.INSTANCE.map(sortedProfessionals);
    }

    /**
     * Processes the keywords from a non-search conversation call, building a response DTO
     * that either prompts for more information or presents the search results.
     *
     * @param searchSessionDTO The search session DTO containing the current state and issue.
     *
     * @return A ResponseDTO containing the next question or search results.
     * @throws IOException If an input or output exception occurs.
     */
    public ResponseDTO processSearch(SearchSessionDTO searchSessionDTO) throws IOException {
        LOGGER.info("Processing search for session: {}", searchSessionDTO.getSessionId());

        // Retrieve or create a new conversation based on the session.
        Conversation conversation = getOrCreateConversation(searchSessionDTO);
        conversation.setCurrentState(searchSessionDTO.getCurrentState());

        // Initialize the response DTO.
        ResponseDTO responseDTO = new ResponseDTO();

        // Process based on the current state of the conversation.
        if (!ConversationState.INITIAL.name().equals(conversation.getCurrentState()) && conversation.getId() == null) {
            LOGGER.error("Initial conversation state is required to proceed.");
            throw new IllegalStateException("Initial conversation state required.");
        } else {
            String nextStep = ConversationState.retrieveNextStep(conversation.getCurrentState());

            if (ConversationState.PAIN_LEVEL.name().equals(conversation.getCurrentState())) {
                conversation.setPainLevel(searchSessionDTO.getIssue());
                prepareHumanPreferences(responseDTO);
            } else {
                conversation.extractNewKeywords(searchSessionDTO, extractKeywords(searchSessionDTO));
            }

            if (conversation.getKeywords().size() < 3) {
                conversation.setNextAction(nextStep);
            }
            System.out.println(conversation.getNonKeywords());
            if (ConversationState.SEARCH.name().equals(nextStep) && conversation.getNonKeywords().size() <= 3) {
                List<ProfessionalDTO> professionals = performSearchProfessional(searchSessionDTO, conversation);
                responseDTO.setProfessionnals(professionals);
            } else {
                conversation.setNextAction(nextStep);
            }

            if(nextStep != null){

            Exchange exchange = new Exchange(searchSessionDTO.getIssue(), conversation.getNextQuestion());
            conversation.addExchange(exchange);}

            saveConversation(searchSessionDTO.getUserId(), conversation);
            if(nextStep == null) {
                String nextQuestion = "Please wait, we will get back to you";
                responseDTO.buildResponseFromConversation(conversation, nextQuestion);
            }
            else responseDTO.buildResponseFromConversation(conversation, conversation.getNextQuestion());
            return responseDTO;
        }
    }

    /**
     * Prepares a list of human preferences to be included in the response.
     * This method fetches all human preferences from the repository, extracts their names,
     * and sets them in the response DTO for the client to display or use.
     *
     * @param responseDTO The response DTO to which the preferences will be added.
     */
    private void prepareHumanPreferences(ResponseDTO responseDTO) {
        LOGGER.debug("Preparing human preferences for response");
        final List<HumanPreference> humanPreferences = humanPreferencesRepository.findAll();
        List<String> preferenceNames = humanPreferences.stream().map(humanPreference -> humanPreference.getName().getValue()).collect(Collectors.toList());

        if(preferenceNames.isEmpty()){
        List<String> preferenceNames1 = Arrays.stream(HumanPreferenceEnum.values())
                .map(HumanPreferenceEnum::name)
                .collect(Collectors.toList());
        responseDTO.setPreferences(preferenceNames1);}
        else responseDTO.setPreferences(preferenceNames);
    }

    /**
     * Extracts keywords from the search session DTO's issue text.
     * This method tokenizes the issue text, filters out non-relevant words, and matches the remaining tokens
     * against known keywords and their synonyms. It separates found keywords and non-keywords.
     *
     * @param searchSessionDTO The search session DTO containing the issue text to analyze.
     *
     * @return A KeywordsResult object containing lists of found keywords and non-keywords.
     */
    public KeywordsResult extractKeywords(SearchSessionDTO searchSessionDTO) {
        LOGGER.debug("Extracting keywords for session {}", searchSessionDTO.getSessionId());
        String combinedText = Optional.ofNullable(searchSessionDTO.getIssue()).orElse("");

        // Retrieve all keywords from specialities and flatten into a list
        List<String> allKeywords =
                specialityRepository.findAll().stream().flatMap(speciality -> speciality.getKeywords().stream()).distinct().toList();

        // Tokenize the combined text and filter out non-relevant words
        String[] tokens = SimpleTokenizer.INSTANCE.tokenize(combinedText);
        List<String> relevantTokens =
                Arrays.stream(tokens).filter(token -> !SearchUtils.nonRelevantWords.contains(token.toLowerCase())).toList();

        // Separate tokens into found keywords and non-keywords
        List<String> foundKeywords = new ArrayList<>();
        List<String> nonKeywords = new ArrayList<>();
        for (String token : relevantTokens) {
            Optional<String> match = allKeywords.stream()
                                                .filter(keyword -> keyword.equalsIgnoreCase(token) || keyword.equalsIgnoreCase(
                                                        findMatchingKeywordSynonym(token)) || SearchUtils.isSimilar(keyword, token))
                                                .findFirst();
            if (match.isPresent()) {
                foundKeywords.add(match.get());
            } else {
                nonKeywords.add(token);
            }
        }

        return new KeywordsResult(foundKeywords, nonKeywords);
    }

    /**
     * Finds a matching synonym for a given keyword.
     * This method checks the synonym dictionary for a direct match or a contained synonym.
     *
     * @param word The word to find a synonym for.
     *
     * @return The matching keyword if a synonym is found; otherwise, null.
     */
    public String findMatchingKeywordSynonym(String word) {
        LOGGER.debug("Finding synonym for word: {}", word);
        return SearchUtils.synonymDictionary.entrySet()
                                            .stream()
                                            .filter(entry -> entry.getKey().equalsIgnoreCase(word) || entry.getValue().contains(word.toLowerCase()))
                                            .map(Map.Entry::getKey)
                                            .findFirst()
                                            .orElse(null);
    }

    /**
     * Retrieves an existing conversation or creates a new one based on the search session ID.
     * If a conversation with the given session ID exists, it is returned; otherwise, a new conversation
     * is created with the initial state set from the search session DTO.
     *
     * @param searchSessionDTO The search session DTO containing the session ID and initial state.
     *
     * @return The existing or new conversation.
     */
    public Conversation getOrCreateConversation(SearchSessionDTO searchSessionDTO) {
        LOGGER.debug("Retrieving or creating conversation for session {}", searchSessionDTO.getSessionId());
        return Optional.ofNullable(searchSessionDTO.getSessionId()).flatMap(conversationRepository::findById).orElseGet(() -> {
            Conversation newConversation = new Conversation();
            newConversation.setCurrentState(searchSessionDTO.getCurrentState());
            return newConversation;
        });
    }

    /**
     * Saves the conversation to the repository and links it to the specified user.
     * This method first saves the conversation and then updates the user's conversation list
     * to include the saved conversation.
     *
     * @param userId The ID of the user to link the conversation to.
     * @param conversation The conversation to be saved and linked.
     */
    public void saveConversation(String userId, Conversation conversation) {
        LOGGER.info("Saving conversation {} for user {}", conversation.getId(), userId);
        conversationRepository.save(conversation);
        linkConversationToUser(userId, conversation);
    }

    /**
     * Links a conversation to a user by updating the user's conversation list.
     * This method fetches the user by ID, adds the conversation to the user's list of conversations,
     * and saves the updated user back to the repository.
     *
     * @param userId The ID of the user to link the conversation to.
     * @param conversation The conversation to be linked to the user.
     */
    public void linkConversationToUser(String userId, Conversation conversation) {
        LOGGER.debug("Linking conversation {} to user {}", conversation.getId(), userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
        user.addConversation(conversation);
        userRepository.save(user);
    }
}
