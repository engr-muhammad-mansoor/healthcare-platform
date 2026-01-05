package com.healthcare.uman.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.uman.dto.user.ProfessionalDTO;
import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.mapper.UserMapper;
import com.healthcare.uman.model.professional.ProfessionalCard;
import com.healthcare.uman.model.user.Patient;
import com.healthcare.uman.model.user.Professional;
import com.healthcare.uman.repository.ProfessionalRepository;

@Service
public class ProfessionalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionalService.class);
    private final ProfessionalRepository professionalRepository;
    private final UserService userService;

    @Autowired
    public ProfessionalService(ProfessionalRepository professionalRepository, UserService userService) {
        this.professionalRepository = professionalRepository;
        this.userService = userService;
    }

    public void updateProfessional(ProfessionalDTO professionalDTOpr, String id) throws UserNotFoundException {
        LOGGER.debug("Updating professional with ID: {} with information: {}", id, professionalDTOpr);

        Professional professional = professionalRepository.findById(id)
                                                          .orElseThrow(() -> new UserNotFoundException("No professional exists with ID: " + id));

        Professional updatedProfessional = UserMapper.INSTANCE.map(professionalDTOpr);

        userService.updateUser(professionalDTOpr, id);

        Optional.ofNullable(updatedProfessional.getCalendars()).ifPresent(professional::setCalendars);
        Optional.ofNullable(updatedProfessional.getSubscriptionEnum()).ifPresent(professional::setSubscriptionEnum);
        Optional.ofNullable(updatedProfessional.getBankInformation()).ifPresent(professional::updateBankInformation);

        if (updatedProfessional.getProfessionalCard() != null) {
            updateProfessionalCard(updatedProfessional.getProfessionalCard(), professional.getProfessionalCard());
        }

        professionalRepository.save(professional);
        LOGGER.info("Professional with ID: {} updated successfully", id);
    }

    public void updateProfessionalCard(ProfessionalCard updatedCard, ProfessionalCard professionalCard) {
        LOGGER.debug("Updating professional card details");

        Optional.ofNullable(updatedCard.getPresentation()).ifPresent(professionalCard::setPresentation);
        Optional.ofNullable(updatedCard.getLanguages()).ifPresent(professionalCard::setLanguages);
        Optional.ofNullable(updatedCard.getSpecialities()).ifPresent(specialities -> professionalCard.updateSpecialities(specialities));
        Optional.ofNullable(updatedCard.getProfessionalAddresses()).ifPresent(professionalCard::setProfessionalAddresses);
        Optional.ofNullable(updatedCard.getDiplomes()).ifPresent(professionalCard::setDiplomes);
        Optional.ofNullable(updatedCard.getExperiences()).ifPresent(experiences -> professionalCard.updateExperiences(experiences));
        Optional.ofNullable(updatedCard.getPaymentModeEnum()).ifPresent(professionalCard::setPaymentModeEnum);
        Optional.ofNullable(updatedCard.getPhotos()).ifPresent(professionalCard::setPhotos);
        professionalCard.setAllowNewPatient(updatedCard.isAllowNewPatient());
        Optional.ofNullable(updatedCard.getOpeningHours()).ifPresent(hours -> professionalCard.updateOpeningHours(hours));
        Optional.ofNullable(updatedCard.getPricings()).ifPresent(pricings -> professionalCard.updatePricings(pricings));
    }


}
