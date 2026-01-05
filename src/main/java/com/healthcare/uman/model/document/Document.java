package com.healthcare.uman.model.document;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.healthcare.uman.model.user.User;

@org.springframework.data.mongodb.core.mapping.Document(collection = "document")
public class Document implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;

    private DocumentTypeEnum documentTypeEnum;

    @DBRef
    private User idPatient;

    @DBRef
    private User idPro;

    private Date createdDate;

    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentTypeEnum getDocumentTypeEnum() {
        return documentTypeEnum;
    }

    public void setDocumentTypeEnum(DocumentTypeEnum documentTypeEnum) {
        this.documentTypeEnum = documentTypeEnum;
    }

    public User getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(User idPatient) {
        this.idPatient = idPatient;
    }

    public User getIdPro() {
        return idPro;
    }

    public void setIdPro(User idPro) {
        this.idPro = idPro;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
