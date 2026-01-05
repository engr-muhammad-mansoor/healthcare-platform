package com.healthcare.uman.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.uman.annotation.SupervisorLogAudit;
import com.healthcare.uman.dto.booking.CalendarDTO;
import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.mapper.CalendarMapper;
import com.healthcare.uman.model.booking.Calendar;
import com.healthcare.uman.service.CalendarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@RestController
@Tag(name = "Calendar API")
@RequestMapping("/api/v1/calendars")
public class CalendarController {

/*

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @SupervisorLogAudit
    @PostMapping
    @Operation(summary = "Create a new calendar", description = "Returns a calendar")
    public ResponseEntity<CalendarDTO> addCalendar(@Valid @RequestBody CalendarDTO calendar) throws UserNotFoundException {
        LOGGER.debug("Call addCalendar with informations : {}", calendar.toString());
        final Calendar calendarSaved = calendarService.createCalendar(calendar);
        return ResponseEntity.ok().body(CalendarMapper.INSTANCE.map(calendarSaved));
    }

    @SupervisorLogAudit
    @GetMapping("/users/{id}")
    @Operation(summary = "Get a calendar's user by user Id", description = "Get a calendar's user by user Id")
    public ResponseEntity<CalendarDTO> getCalendarByUserId(@PathVariable String id, @RequestParam(name = "months", defaultValue = "3") int numberOfMonths) {
        try {
            return ResponseEntity.ok().body(CalendarMapper.INSTANCE.map(calendarService.getCalendarByUserId(id, numberOfMonths)));
        } catch (Exception e) {
            LOGGER.error("Failed to get user account: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @SupervisorLogAudit
    @PutMapping("/{id}")
    @Operation(summary = "Update a calendar's information", description = "Update a calendar's information")
    public ResponseEntity<String> updateCalendar(@RequestBody CalendarDTO calendarDTO, @PathVariable String id) {
        calendarService.updateCalendar(calendarDTO, id);
        LOGGER.debug("Calendar updated with id: {}", id);
        return ResponseEntity.ok("Calendar updated successfully");
    }

    @SupervisorLogAudit
    @DeleteMapping("/{id}")
    @Operation(summary = "Disable a calendar", description = "Disable a calendar")
    public ResponseEntity<String> disableCalendar(@PathVariable String id) {
        try {
            calendarService.disableCalendar(id);
            LOGGER.debug("Calendar disabled");
            return ResponseEntity.ok("Calendar disabled successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to delete Calendar: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
*/
}
