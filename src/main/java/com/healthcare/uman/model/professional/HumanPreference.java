package com.healthcare.uman.model.professional;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.model.speciality.HumanPreferenceEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "humanPreference")
public class HumanPreference implements Serializable {

    @Id
    private String id;

    private HumanPreferenceEnum name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HumanPreferenceEnum getName() {
        return name;
    }

    public void setName(HumanPreferenceEnum name) {
        this.name = name;
    }
}
