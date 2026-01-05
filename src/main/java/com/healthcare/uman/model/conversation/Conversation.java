package com.healthcare.uman.model.conversation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.uman.dto.search.SearchSessionDTO;
import com.healthcare.uman.model.user.User;

@Document(collection = "conversation")
public class Conversation implements Serializable {

    @Id
    private String id;
    private List<Exchange> exchanges = new ArrayList<>();
    private String currentState;
    private String nextAction;
    private List<String> keywords;
    private List<String> nonKeywords;
    private List<String> humanPreferences;

    private String painLevel;
    @DBRef
    private User user;

    public String getPainLevel() {
        return painLevel;
    }

    public void setPainLevel(String painLevel) {
        this.painLevel = painLevel;
    }

    public List<String> getHumanPreferences() {
        return humanPreferences;
    }

    public void setHumanPreferences(List<String> humanPreferences) {
        this.humanPreferences = humanPreferences;
    }

    public List<String> getNonKeywords() {
        return nonKeywords;
    }

    public void setNonKeywords(List<String> nonKeywords) {
        this.nonKeywords = nonKeywords;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Exchange> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<Exchange> exchanges) {
        this.exchanges = exchanges;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    public void addExchange(Exchange exchange) {
        this.exchanges.add(exchange);
    }

    /**
     * Determines the next question based on the current state of the conversation.
     *
     * @return The next question to be asked.
     */
    public String getNextQuestion() {
        Map<String, String> questions = Map.of("INITIAL", "Pouvez-vous me décrire votre problématique ?",
                "DETAIL", "Pouvez-vous me donner plus de détails sur comment vous avez eu cette douleur ?",
                "PAIN_LEVEL", "Quel est votre niveau de douleur ?",
                "HUMAN_PREFERENCES", "Quelles qualités humaines sont les plus importantes pour le praticien ?", "SEARCH",
                "J'aurai besoin de plus de détails sur votre problèmatique, précisez-moi la localité");

        return questions.getOrDefault(this.getNextAction(), "Recherche lancée");
    }

    public int extractNewKeywords(SearchSessionDTO searchSessionDTO, KeywordsResult keywords) {
        // Retrieve new keywords
        Set<String> currentKeywordsSet = new HashSet<>(Optional.ofNullable(this.getKeywords()).orElseGet(ArrayList::new));
        List<String> newKeywords = keywords.getFoundKeywords().stream()
                                           .filter(currentKeywordsSet::add)
                                           .toList();

        this.setKeywords(new ArrayList<>(currentKeywordsSet));

        int newKeywordsCount = newKeywords.size();

        // Retrieve non keywords
        Set<String> nonCurrentWords = new HashSet<>(Optional.ofNullable(this.getNonKeywords()).orElseGet(ArrayList::new));
        List<String> nonCurrent = keywords.getNonKeywords().stream()
                                          .filter(nonCurrentWords::add)
                                          .toList();

        this.setNonKeywords(new ArrayList<>(nonCurrent));

        if ("HUMAN_PREFERENCES".equals(this.getCurrentState())) {
            this.setHumanPreferences(searchSessionDTO.getHumanPreferences());
        }

        return newKeywordsCount;
    }

}

