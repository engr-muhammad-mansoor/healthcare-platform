package com.healthcare.uman.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.healthcare.uman.annotation.SupervisorLogAudit;
import com.healthcare.uman.dto.search.ResponseDTO;
import com.healthcare.uman.dto.search.SearchSessionDTO;
import com.healthcare.uman.service.SearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Search API")
@RequestMapping("/api/v1/search")
public class SearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @SupervisorLogAudit
    @PostMapping
    @Operation(summary = "Search by speciality", description = "Search by speciality")
    public ResponseEntity<Object> handleSearchSession(@RequestBody SearchSessionDTO searchServiceSession) {
        try {
            ResponseDTO response = searchService.processSearch(searchServiceSession);
            return ResponseEntity.ok().body(response);
        } catch (IllegalStateException e) {
            LOGGER.error("Erreur de traitement : {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            LOGGER.error("Erreur de traitement : {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
