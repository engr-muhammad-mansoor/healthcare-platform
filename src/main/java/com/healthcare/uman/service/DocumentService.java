package com.healthcare.uman.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.model.booking.Calendar;
import com.healthcare.uman.model.document.Document;
import com.healthcare.uman.repository.DocumentRepository;

@Service
public class DocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentService.class);
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    /**
     * Add a new Document
     *
     * @param document
     *
     * @return
     */
    public Document createDocument(Document document) {
        LOGGER.debug("Start createCalendar with informations : {}", document.toString());
        return documentRepository.save(document);
    }

    /**
     * Get a document by ID
     *
     * @param id
     */
    public Document getDocument(String id) throws UserNotFoundException {
        LOGGER.debug("getDocument for document Id : {}", id);

        final Optional<Document> document = documentRepository.findById(id);
        if (document.isPresent()) {
            LOGGER.debug("getDocument for document : {} - document existing", id);
            return document.get();
        }
        throw new UserNotFoundException("Aucun document n'existe");
    }

    /**
     * Get a documents by patient's id
     *
     * @param id
     */
    public List<Document> getDocumentsByPatient(String id) throws UserNotFoundException {
        LOGGER.debug("getDocumentsByUser for documents for user Id : {}", id);

        final List<Document> documents = documentRepository.findByIdPatient(id);
        if (documents != null) {
            LOGGER.debug("getDocumentsByPatient for id patient : {} ", id);
            return documents;
        }
        throw new UserNotFoundException("Aucun document n'existe");
    }

    /**
     * Get a documents by pro's id
     *
     * @param id
     */
    public List<Document> getDocumentsByPro(String id) throws UserNotFoundException {
        LOGGER.debug("getDocumentsByPro for documents for user Id : {}", id);

        final List<Document> documents = documentRepository.findByIdPro(id);
        if (documents != null) {
            LOGGER.debug("getDocumentsByPro for id pro : {} ", id);
            return documents;
        }
        throw new UserNotFoundException("Aucun document n'existe");
    }

    /**
     * Update a document
     *
     * @param name
     * @param id
     *
     * @return
     */
    public Document updateDocument(String name, String id) {
        LOGGER.debug("updateDocument for the document id : {}", id);

        Optional<Document> document = documentRepository.findById(id);
        if (document.isPresent()) {
            Document existingDocument = document.get();

            if (name != null) {
                existingDocument.setName(name);
            }
            return documentRepository.save(existingDocument);
        }
        return null;
    }

    /**
     * Delete a document by ID
     *
     * @param id
     */
    public void deleteDocument(String id) {
        LOGGER.debug("Disable the document id : {}", id);
        final Optional<Document> documentToDelete = documentRepository.findById(id);
        if (documentToDelete.isPresent()) {
            Document calendar = documentToDelete.get();
            calendar.setActive(false);
            documentRepository.save(calendar);
        }
    }

    public List<Calendar> getCalendarForMonths(String calendarId, int numberOfMonths) {

        LocalDate currentDate = LocalDate.now();
        List<Calendar> calendar = new ArrayList<>();

        LocalDate monthStart = currentDate.plusMonths(numberOfMonths).withDayOfMonth(1);
        LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

        return calendar;
    }
}

