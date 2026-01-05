package com.healthcare.uman.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import com.healthcare.uman.dto.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.healthcare.uman.annotation.SupervisorLogAudit;
import com.healthcare.uman.dto.user.PatientDTO;
import com.healthcare.uman.dto.user.ProfessionalDTO;
import com.healthcare.uman.dto.user.UserDTO;
import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.mapper.UserMapper;
import com.healthcare.uman.model.connexion.ResetPassword;
import com.healthcare.uman.model.connexion.UserLogin;
import com.healthcare.uman.model.user.Patient;
import com.healthcare.uman.model.user.Photo;
import com.healthcare.uman.model.user.Professional;
import com.healthcare.uman.service.PatientService;
import com.healthcare.uman.service.PhotoService;
import com.healthcare.uman.service.ProfessionalService;
import com.healthcare.uman.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users API")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final PatientService patientService;
    private final PhotoService photoService;
    private final ProfessionalService professionalService;

    public UserController(UserService userService, PatientService patientService, PhotoService photoService, ProfessionalService professionalService) {
        this.userService = userService;
        this.patientService = patientService;
        this.photoService = photoService;
        this.professionalService = professionalService;
    }




    @SupervisorLogAudit
    @Operation(summary = "Create a new pro user", description = "Returns a string")
    @PostMapping("/signup/pro")
    public ResponseEntity<String> registerProUser(@Valid @RequestBody ProfessionalDTO request) {
        LOGGER.debug("Call registerUser with informations : {}", request.toString());
        userService.registerProUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @SupervisorLogAudit
    @Operation(summary = "Create a new patient", description = "Returns a string")
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody PatientDTO request) {
        LOGGER.debug("Call registerUser with informations : {}", request.toString());
        userService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @SupervisorLogAudit
    @Operation(summary = "Login for an user - must includes Google / Facebook Oauth", description = "By sending a UserLogin objet")
    @PostMapping(path = "/signin")
    public ResponseEntity<JwtResponse> UserLogin(@RequestBody UserLogin request) throws Exception {
        userService.doAuthenticate(request.getEmail(),request.getPassword());
        JwtResponse response=userService.loginUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @SupervisorLogAudit
    @PostMapping("/signout")
    @Operation(summary = "logout for an user")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logoutUser(request, response);
        LOGGER.debug("User logged out successfully");
        return ResponseEntity.ok("User logged out successfully");
    }

    @SupervisorLogAudit
    @PutMapping("/{id}")
    @Operation(summary = "Update basic profile user", description = "Update the basic user's information")
    public ResponseEntity<String> updateProfile(@RequestBody UserDTO user, @PathVariable String id) throws UserNotFoundException {
        userService.updateUser(user, id);
        LOGGER.debug("User profile updated: {} with id: {}", user.getEmail(), id);
        return ResponseEntity.ok("User profile updated successfully");
    }

    @SupervisorLogAudit
    @PutMapping("/patient/{id}")
    @Operation(summary = "Update a patientDTO's user", description = "Update the information about a patientDTO")
    public ResponseEntity<String> updateProfilePatient(@RequestBody PatientDTO patientDTO, @PathVariable String id) throws UserNotFoundException {
        patientService.updateUser(patientDTO, id);
        LOGGER.debug("User profile updated: {} with id: {}", patientDTO.getEmail(), id);
        return ResponseEntity.ok("User profile updated successfully");
    }

    @SupervisorLogAudit
    @PutMapping("/pro/{id}")
    @Operation(summary = "Update a professionalDTO's user", description = "Update the information about a professionalDTO")
    public ResponseEntity<String> updateProfilePro(@RequestBody ProfessionalDTO professionalDTO, @PathVariable String id) throws UserNotFoundException {
        professionalService.updateProfessional(professionalDTO, id);
        LOGGER.debug("User profile updated: {} with id: {}", professionalDTO.getEmail(), id);
        return ResponseEntity.ok("User profile updated successfully");
    }

    @SupervisorLogAudit
    @PostMapping("/reset")
    @Operation(summary = "Reset password", description = "Reset the user's password by generating a new one")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPassword resetPasswordDTO) {
        try {
            userService.resetPassword(resetPasswordDTO);
            LOGGER.debug("Password reset request submitted");
            return ResponseEntity.ok("Password reset request submitted successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to reset password: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @SupervisorLogAudit
    @PostMapping("/changePassword")
    @Operation(summary = "Change password", description = "Change the user's password")
    public ResponseEntity<String> changePassword(@RequestBody ResetPassword resetPasswordDTO) {
        try {
            userService.changePassword(resetPasswordDTO);
            LOGGER.debug("Password reset request submitted");
            return ResponseEntity.ok("Password reset request submitted successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to reset password: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @SupervisorLogAudit
    @PostMapping("/photos/add")
    @Operation(summary = "Add a new user's profile", description = "Change the user's photo")
    public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image) throws IOException {
        String id = photoService.addPhoto(title, image);
        return "redirect:/photos/" + id;
    }

    @GetMapping("/photos/{id}")
    @Operation(summary = "Get the user's photo", description = "Get the user's photo")
    public String getPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);
        return "photos";
    }

    @SupervisorLogAudit
    @PutMapping("/status/{id}/{status}")
    @Operation(summary = "Change the user's status", description = "Disable the user's account")
    public ResponseEntity<String> statusAccount(@PathVariable String id, @PathVariable boolean status) {
        try {
            userService.changeStatus(id, status);
            LOGGER.debug("User's status is modified");
            return ResponseEntity.ok("User status modified successfully to "+ status);
        } catch (Exception e) {
            LOGGER.error("Failed to modified status user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @SupervisorLogAudit
    @GetMapping("/{id}")
    @Operation(summary = "Get user's informations", description = "Get all information about the user")
    public ResponseEntity<Object> getUser(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(userService.getUser(id));
        } catch (Exception e) {
            LOGGER.error("Failed to get user account: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> catchException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                String fieldName = fieldError.getField();
                String errorMessage = fieldError.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            } else {
                // Handle other types of validation errors if needed
                String errorMessage = error.getDefaultMessage();
                errors.put("global", errorMessage); // Use "global" or any other appropriate key
            }
        });

        return errors;
    }


}
