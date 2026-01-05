package com.healthcare.uman.dto.search;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.dto.user.ProfessionalDTO;
import com.healthcare.uman.model.conversation.Conversation;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO implements Serializable {

    private String question;
    private String nextState;
    private String conversationId;
    private List<String> preferences;
    private String userId;
    private List<ProfessionalDTO> professionnals;

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ProfessionalDTO> getProfessionnals() {
        return professionnals;
    }

    public void setProfessionnals(List<ProfessionalDTO> professionnals) {
        this.professionnals = professionnals;
    }

    /**
     * Builds the response DTO from the conversation details and the next question.
     *
     * @param conversation The conversation containing the exchange history.
     * @param nextQuestion The next question to be presented to the user.
     *
     * @return The updated response DTO.
     */
    public void buildResponseFromConversation(Conversation conversation, String nextQuestion) {
        clearSensitiveData(this.getProfessionnals());
        this.setConversationId(conversation.getId());
        if (this.getProfessionnals() == null || this.getProfessionnals().isEmpty()) {
            this.setQuestion(nextQuestion);
            this.setNextState(conversation.getNextAction());
        }
    }

    /**
     * Clears sensitive data from a list of professional DTOs.
     *
     * @param professionals The list of professional DTOs to be cleaned.
     */
    private void clearSensitiveData(List<ProfessionalDTO> professionals) {
        if (professionals != null) {
            professionals.forEach(professionalDTO -> professionalDTO.clearSensitiveDataForProfessional());
        }
    }
}
