package com.healthcare.uman.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.healthcare.uman.model.document.Document;

public interface DocumentRepository extends MongoRepository<Document, String> {

    List<Document> findByIdPatient(String idUser);

    List<Document> findByIdPro(String idUser);

}
