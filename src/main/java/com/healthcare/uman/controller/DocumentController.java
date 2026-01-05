package com.healthcare.uman.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.uman.annotation.SupervisorLogAudit;
import com.healthcare.uman.dto.document.DocumentDTO;
import com.healthcare.uman.mapper.DocumentMapper;
import com.healthcare.uman.model.document.Document;
import com.healthcare.uman.service.DocumentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Document API")
@RequestMapping("/api/v1/documents")
public class DocumentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @SupervisorLogAudit
    @PostMapping
    @Operation(summary = "Create a new document, adding the document to user", description = "Returns a document")
    public ResponseEntity<DocumentDTO> addDocument(@Valid @RequestBody DocumentDTO document) {
        LOGGER.debug("Call addDocument with informations : {}", document.toString());
        final Document documentSaved = documentService.createDocument(DocumentMapper.INSTANCE.map(document));
        return ResponseEntity.ok().body(DocumentMapper.INSTANCE.map(documentSaved));
    }

    @SupervisorLogAudit
    @GetMapping("/patient/{id}")
    @Operation(summary = "Get the documents of user by patient Id", description = "Get the documents of user by patient Id")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByPatientId(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(DocumentMapper.INSTANCE.map(documentService.getDocumentsByPatient(id)));
        } catch (Exception e) {
            LOGGER.error("Failed to get documents: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @SupervisorLogAudit
    @GetMapping("/pro/{id}")
    @Operation(summary = "Get the documents of user by professional Id", description = "Get the documents of user by professional Id")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByProfessionalId(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(DocumentMapper.INSTANCE.map(documentService.getDocumentsByPro(id)));
        } catch (Exception e) {
            LOGGER.error("Failed to get documents: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @SupervisorLogAudit
    @PutMapping("/{id}")
    @Operation(summary = "Update a document's information", description = "Update a document's information")
    public ResponseEntity<String> updateDocument(@RequestBody String name, @PathVariable String id) {
        documentService.updateDocument(name, id);
        LOGGER.debug("Document updated with id: {}", id);
        return ResponseEntity.ok("Document updated successfully");
    }

    @SupervisorLogAudit
    @DeleteMapping("/{id}")
    @Operation(summary = "Disable a document", description = "Disable a document")
    public ResponseEntity<String> deleteDocument(@PathVariable String id) {
        try {
            documentService.deleteDocument(id);
            LOGGER.debug("Document disabled");
            return ResponseEntity.ok("Document disabled successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to delete document: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
