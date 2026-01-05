package com.healthcare.uman.service;

import com.healthcare.uman.dto.JwtResponse;
import com.healthcare.uman.dto.user.PatientDTO;
import com.healthcare.uman.dto.user.ProfessionalDTO;
import com.healthcare.uman.dto.user.UserDTO;
import com.healthcare.uman.exception.InvalidEmailException;
import com.healthcare.uman.exception.InvalidPasswordException;
import com.healthcare.uman.exception.UserAlreadyExistsException;
import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.mapper.UserMapper;
import com.healthcare.uman.model.connexion.ResetPassword;
import com.healthcare.uman.model.connexion.UserLogin;
import com.healthcare.uman.model.professional.HumanPreference;
import com.healthcare.uman.model.speciality.Speciality;
import com.healthcare.uman.model.speciality.SpecialityPrice;
import com.healthcare.uman.model.user.Patient;
import com.healthcare.uman.model.user.Professional;
import com.healthcare.uman.model.user.User;
import com.healthcare.uman.model.user.UserEnum;
import com.healthcare.uman.repository.*;
import com.healthcare.uman.security.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final SpecialityRepository specialityRepository;
    private final HumanPreferencesRepository humanPreferencesRepository;

    private final ProfessionalRepository professionalRepository;

    private final DatabaseUserDetailsService userDetailsService;
    private final EmailService emailService;
    private final DatabaseUserDetailPasswordService userDetailPasswordService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper jwtHelper;


    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, PatientRepository patientRepository, SpecialityRepository specialityRepository, HumanPreferencesRepository humanPreferencesRepository, ProfessionalRepository professionalRepository, DatabaseUserDetailsService userDetailsService, EmailService emailService, DatabaseUserDetailPasswordService userDetailPasswordService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.patientRepository = patientRepository;
        this.specialityRepository = specialityRepository;
        this.humanPreferencesRepository = humanPreferencesRepository;
        this.professionalRepository = professionalRepository;
        this.userDetailsService = userDetailsService;
        this.emailService = emailService;
        this.userDetailPasswordService = userDetailPasswordService;
    }

    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            SecurityContextHolder.clearContext();
            LOGGER.debug("User logged out successfully");
        }
    }

    public JwtResponse loginUser(UserLogin loginDTO) {
        final String email = loginDTO.getEmail();
        LOGGER.debug("loginUser for user : {}", email);
        UserDTO userDTO = null;
        try {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if (userDetails == null) {
                throw new AuthenticationServiceException("Invalid mail");
            }
            final boolean matchesPassword = passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword());
            JwtResponse response = null;
            if (matchesPassword) {
                userDTO = UserMapper.INSTANCE.mapUserDto(userRepository.findByEmail(email));
                String token = this.jwtHelper.generateToken(userDetails);
                response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
                response.setUser(userDTO);
            }
            return response;
        } catch (UsernameNotFoundException e) {
            // Log failed login attempt
            LOGGER.warn("Failed login attempt for user: {}", email);
            throw new AuthenticationServiceException("Invalid mail");
        } catch (AuthenticationException e) {
            // Log failed login attempt
            LOGGER.warn("Failed login attempt for user: {}", email);
            throw new AuthenticationServiceException("Invalid username or password");
        }
    }

    public void registerUser(PatientDTO registrationRequest) {
        LOGGER.debug("Starting registerUser with informations : {}", registrationRequest.toString());

        User existingUser = userRepository.findByEmail(registrationRequest.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with the same email already exists.");
        }

        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!registrationRequest.getEmail().matches(emailPattern)) {
            throw new InvalidEmailException("Invalid email format.");
        }

        int minimumPasswordLength = 8;
        if (registrationRequest.getPassword().length() < minimumPasswordLength) {
            throw new InvalidPasswordException("Password should be at least 8 characters long.");
        }

        LOGGER.debug("Encoding password : {}", registrationRequest.getEmail());

        // Encoding password security
        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        Patient patient = UserMapper.INSTANCE.map(registrationRequest);
        patient.setPassword(encodedPassword);
        userRepository.save(patient);
        // sendWelcomeEmail(userEmail, userName); // Uncomment and pass actual user email and name when needed
    }


    public void sendWelcomeEmail(String to, String name) {
        Context context = new Context();
        context.setVariable("name", name);

        emailService.sendEmail(to, "Welcome!", "welcome-email", context);
    }

    public void registerProUser(ProfessionalDTO registrationRequest) {
        LOGGER.debug("Starting registerUser with informations : {}", registrationRequest.toString());

        User existingUser = userRepository.findByEmail(registrationRequest.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with the same email already exists.");
        }

        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!registrationRequest.getEmail().matches(emailPattern)) {
            throw new InvalidEmailException("Invalid email format.");
        }

        int minimumPasswordLength = 8;
        if (registrationRequest.getPassword().length() < minimumPasswordLength) {
            throw new InvalidPasswordException("Password should be at least 8 characters long.");
        }

        LOGGER.debug("Encoding password : {}", registrationRequest.getEmail());

        // Encoding password security
        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        Professional professional = UserMapper.INSTANCE.map(registrationRequest);

        // Retrieve speciality in db
        if (professional.getProfessionalCard().getSpecialities() != null) {
            for (SpecialityPrice specialityPrice : professional.getProfessionalCard().getSpecialities()) {
                final Speciality specialityRepositoryByName = specialityRepository.findByName(specialityPrice.getSpeciality().getName().name());
                specialityPrice.setSpeciality(specialityRepositoryByName);
            }
        }

        if (professional.getProfessionalCard().getHumanPreferences() != null) {
            for (HumanPreference humanPreference : professional.getProfessionalCard().getHumanPreferences()) {
                final HumanPreference humanPreferencesRepositoryByName = humanPreferencesRepository.findByName(humanPreference.getName().name());
                humanPreference.setId(humanPreferencesRepositoryByName.getId());
            }
        }

        professional.setPassword(encodedPassword);
        userRepository.save(professional);
    }

    /**
     * Update profile's information
     *
     * @param id
     * @throws UserNotFoundException
     */
    public void updateUser(UserDTO userDTO, String id) throws UserNotFoundException {
        LOGGER.debug("updateUser for user : {}", id);
        User updatedUser = UserMapper.INSTANCE.map(userDTO);
        User userBdd = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found."));

        if (userBdd != null) {
            LOGGER.debug("updateUser for user : {}, the user is existing", id);

            if (updatedUser.getFirstName() != null) {
                userBdd.setFirstName(updatedUser.getFirstName());
                LOGGER.debug("Updated firstName for user ID: {}", id);
            }

            if (updatedUser.getLastName() != null) {
                userBdd.setLastName(updatedUser.getLastName());
                LOGGER.debug("Updated lastName for user ID: {}", id);
            }

            if (updatedUser.getLastNameMarried() != null) {
                userBdd.setLastNameMarried(updatedUser.getLastNameMarried());
            }

            if (updatedUser.getEmail() != null) {
                userBdd.setEmail(updatedUser.getEmail());
            }

            if (updatedUser.getUsername() != null) {
                userBdd.setUsername(updatedUser.getUsername());
            }

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
                userBdd.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                LOGGER.debug("Updated password for user ID: {}", id);
            }

            if (updatedUser.getProfile() != null) {
                userBdd.setProfile(updatedUser.getProfile());
            }

            if (updatedUser.getDatePassword() != null) {
                userBdd.setDatePassword(updatedUser.getDatePassword());
            }

            if (updatedUser.getBirthDate() != null) {
                userBdd.setBirthDate(updatedUser.getBirthDate());
            }

            if (updatedUser.getBirthCity() != null) {
                userBdd.setBirthCity(updatedUser.getBirthCity());
            }

            if (updatedUser.getType() != null) {
                userBdd.setType(updatedUser.getType());
            }

            if (updatedUser.getCreationDate() != null) {
                userBdd.setCreationDate(updatedUser.getCreationDate());
            }

            if (updatedUser.getUpdateDate() != null) {
                userBdd.setUpdateDate(updatedUser.getUpdateDate());
            }

            if (updatedUser.getAddress() != null) {
                userBdd.setAddress(updatedUser.getAddress());
            }

            if (updatedUser.getReviewUser() != null) {
                userBdd.setReviewUser(updatedUser.getReviewUser());
            }
            userRepository.save(userBdd);
            LOGGER.info("User with ID: {} successfully updated.", id);

        } else {
            throw new UserNotFoundException("Aucun utilisateur n'existe");
        }
    }

    /**
     * Reset a new password
     *
     * @param resetPasswordDTO
     */
    public void resetPassword(ResetPassword resetPasswordDTO) {

        final String email = resetPasswordDTO.getEmail();
        LOGGER.debug("resetPassword for user : {}", email);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new AuthenticationServiceException("Invalid mail");
        }
        userDetailPasswordService.updatePassword(userDetails, resetPasswordDTO.getPassword());
    }

    /**
     * Change password
     *
     * @param resetPasswordDTO
     */
    public void changePassword(ResetPassword resetPasswordDTO) {
        LOGGER.debug("changePassword for user : {}", resetPasswordDTO.getEmail());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(resetPasswordDTO.getEmail());
        if (userDetails == null) {
            throw new AuthenticationServiceException("Invalid mail");
        }

        if (!passwordEncoder.matches(resetPasswordDTO.getPassword(), userDetails.getPassword())) {
            throw new InvalidPasswordException("Invalid old password");
        }

        userDetailPasswordService.updatePassword(userDetails, resetPasswordDTO.getMatchingPassword());

        LOGGER.debug("changePassword for user : {}, password modified", resetPasswordDTO.getEmail());
    }

    /**
     * Disable used
     */
    public void changeStatus(String id, boolean active) throws UserNotFoundException {
        LOGGER.debug("changeStatus for user : {}, status : {}", id, active);

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            LOGGER.debug("changeStatus for user : {}, status : {} - user existing", id, active);

            User user = userOptional.get();
            if (user != null) {
                user.setActive(active);
            }
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Aucun utilisateur n'existe");
        }
    }

    /**
     * Delete an user by ID
     *
     * @param id
     */
    public void deleteAccount(String id) throws UserNotFoundException {
        LOGGER.debug("deleteAccount for user : {}", id);

        final Optional<User> userToDelete = userRepository.findById(id);
        if (userToDelete.isPresent()) {
            userRepository.delete(userToDelete.get());
        } else {
            throw new UserNotFoundException("Aucun utilisateur n'existe");
        }
    }

    /**
     * Get an user by ID
     *
     * @param id
     */
    public Object getUser(String id) throws UserNotFoundException {
        LOGGER.debug("getUser for user : {}", id);

        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            LOGGER.debug("getUser for user : {} - user existing", id);

            final User userBdd = user.get();
            if (userBdd.getType().equals(UserEnum.PATIENT)) {
                LOGGER.debug("getUser for user : {} - patient type", id);
                final Optional<Patient> byId = patientRepository.findById(id);
                byId.ifPresent(patient -> patient.setPassword(null));
                return byId;
            } else {
                LOGGER.debug("getUser for user : {} - pro type", id);
                final Optional<Professional> byId = professionalRepository.findById(id);
                if (byId.isPresent()) {
                    byId.get().setPassword(null);
                }
                return byId;
            }
        }
        throw new UserNotFoundException("Aucun utilisateur n'existe");
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        if (user == null) {
            throw new AuthenticationException("Authenticated user not found") {
            };
        }
        return user;
    }

    public void doAuthenticate(String email, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        try {
            this.manager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {

            throw new Exception("Invalid Username & Password");
        }
    }
}
