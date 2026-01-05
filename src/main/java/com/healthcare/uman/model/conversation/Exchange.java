package com.healthcare.uman.model.conversation;

import java.io.Serializable;

public class Exchange implements Serializable {
    private String userMessage;
    private String appResponse;

    public Exchange(String userMessage, String appResponse) {
        this.userMessage = userMessage;
        this.appResponse = appResponse;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getAppResponse() {
        return appResponse;
    }

    public void setAppResponse(String appResponse) {
        this.appResponse = appResponse;
    }
}
