package com.healthcare.uman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.uman.annotation.SupervisorLogAudit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/stats")
@Tag(name = "Stats API")
public class StatsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatsController.class);

    public StatsController() {
    }

    @SupervisorLogAudit
    @Operation(summary = "Get user's stats", description = "Get user's stats")
    @GetMapping("/users/{id}")
    public ResponseEntity<String> statsUser(@PathVariable String id) {
        LOGGER.debug("Call statsUser with informations : {}", id);
        return ResponseEntity.ok(null);
    }

}
