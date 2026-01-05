package com.healthcare.uman.dto.document;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class DocumentDTO implements Serializable {

    private String id = UUID.randomUUID().toString();

    private String name;

    private DocumentTypeEnumDTO documentTypeEnum;

    private String idPatient;

    private String idPro;

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

    public DocumentTypeEnumDTO getDocumentTypeEnum() {
        return documentTypeEnum;
    }

    public void setDocumentTypeEnum(DocumentTypeEnumDTO documentTypeEnum) {
        this.documentTypeEnum = documentTypeEnum;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getIdPro() {
        return idPro;
    }

    public void setIdPro(String idPro) {
        this.idPro = idPro;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
