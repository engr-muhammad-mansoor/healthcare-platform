package com.healthcare.uman.dto.search;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchSessionDTO implements Serializable {

    /**
     * ID to follow the conversation between back and front
     */
    private String sessionId;

    /**
     * CurrentState based on ConversationState Enum
     */
    private String currentState;

    /**
     * Sentence of the user
     */
    private String issue;

    /**
     * userId based on the connected's user
     */
    private String userId;

    /**
     * Will be need for the step HUMAN_PREFERENCES
     */
    private List<String> humanPreferences;

    /**
     * City where the customer wants to find a doctor if that's not online
     */
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public List<String> getHumanPreferences() {
        return humanPreferences;
    }

    public void setHumanPreferences(List<String> humanPreferences) {
        this.humanPreferences = humanPreferences;
    }
}
