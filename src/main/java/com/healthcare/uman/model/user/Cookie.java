package com.healthcare.uman.model.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cookie implements Serializable {

    private boolean mandatoryCookie;

    private boolean audimat;

    private boolean prevention;

    private boolean contentPersonnalized;

    private boolean campagnePersonnalized;

    public boolean isMandatoryCookie() {
        return mandatoryCookie;
    }

    public void setMandatoryCookie(boolean mandatoryCookie) {
        this.mandatoryCookie = mandatoryCookie;
    }

    public boolean isAudimat() {
        return audimat;
    }

    public void setAudimat(boolean audimat) {
        this.audimat = audimat;
    }

    public boolean isPrevention() {
        return prevention;
    }

    public void setPrevention(boolean prevention) {
        this.prevention = prevention;
    }

    public boolean isContentPersonnalized() {
        return contentPersonnalized;
    }

    public void setContentPersonnalized(boolean contentPersonnalized) {
        this.contentPersonnalized = contentPersonnalized;
    }

    public boolean isCampagnePersonnalized() {
        return campagnePersonnalized;
    }

    public void setCampagnePersonnalized(boolean campagnePersonnalized) {
        this.campagnePersonnalized = campagnePersonnalized;
    }
}
