package com.healthcare.uman.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.healthcare.uman.dto.user.PatientDTO;
import com.healthcare.uman.dto.user.UserEnumDTO;
import com.healthcare.uman.mapper.UserMapper;
import com.healthcare.uman.model.user.Patient;
import com.healthcare.uman.model.user.User;
import com.healthcare.uman.repository.UserRepository;

@Service
public class OauthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OauthService.class);
    private final UserRepository userRepository;
    @Autowired
    public OauthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Post login Oauth, registering new user if needed
     * @param principal
     */
    public void processPostLogin(OAuth2User principal) {
        LOGGER.debug("Starting processPostLogin with informations : {}", principal.getAttribute("email").toString());

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setEmail(principal.getAttribute("email").toString());
        patientDTO.setFirstName(principal.getAttribute("given_name").toString());
        patientDTO.setLastName(principal.getAttribute("family_name").toString());
        patientDTO.setActive(true);
        patientDTO.setType(UserEnumDTO.PATIENT);
        registerUser(patientDTO);
    }


    public void registerUser(PatientDTO patientDTO) {
        LOGGER.debug("Starting registerUser with informations : {}", patientDTO.toString());

        User existingUser = userRepository.findByEmail(patientDTO.getEmail());
        if (existingUser ==  null) {
            Patient patient = UserMapper.INSTANCE.map(patientDTO);
            userRepository.save(patient);
        }
    }

}
