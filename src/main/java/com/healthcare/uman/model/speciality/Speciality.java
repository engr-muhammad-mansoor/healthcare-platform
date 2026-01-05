package com.healthcare.uman.model.speciality;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "speciality")
public class Speciality implements Serializable {

    @Id
    private String id;

    private Specialization name;
    private DomainMedical domainMedical;
    private List<String> keywords;

    public DomainMedical getDomainMedical() {
        return domainMedical;
    }

    public void setDomainMedical(DomainMedical domainMedical) {
        this.domainMedical = domainMedical;
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

    public Specialization getName() {
        return name;
    }

    public void setName(Specialization name) {
        this.name = name;
    }

}
