package com.healthcare.uman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.uman.annotation.SupervisorLogAudit;
import com.healthcare.uman.dto.user.ProfessionalDTO;
import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.mapper.UserMapper;
import com.healthcare.uman.service.ProfessionalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/pro")
@Tag(name = "Professional API")
public class ProfessionalController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionalController.class);
    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @SupervisorLogAudit
    @PutMapping("/{id}")
    @Operation(summary = "Update the profile professional", description = "Update the professional's information")
    public ResponseEntity<String> updateProfile(@RequestBody ProfessionalDTO professional, @PathVariable String id) throws UserNotFoundException {
        professionalService.updateProfessional(professional, id);
        LOGGER.debug("Professional profile updated: {} with id: {}", professional.getEmail(), id);
        return ResponseEntity.ok("Professional profile updated successfully");
    }

}
