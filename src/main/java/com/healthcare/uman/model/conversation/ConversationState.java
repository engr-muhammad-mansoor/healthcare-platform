package com.healthcare.uman.model.conversation;

public enum ConversationState {

    INITIAL("DETAIL"),
    DETAIL("PAIN_LEVEL"),
    PAIN_LEVEL("HUMAN_PREFERENCES"),
    HUMAN_PREFERENCES("SEARCH"),
    SEARCH(null);

    private final String nextStep;

    ConversationState(String nextStep) {
        this.nextStep = nextStep;
    }

    public String getNextStep() {
        return nextStep;
    }

    public static String retrieveNextStep(String currentState) {
        try {
            ConversationState currentStep = ConversationState.valueOf(currentState);
            return currentStep.getNextStep();
        } catch (IllegalArgumentException e) {
            return "SEARCH";
        }
    }
}
