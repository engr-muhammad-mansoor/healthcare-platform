package com.healthcare.uman.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.uman.dto.user.PatientDTO;
import com.healthcare.uman.exception.UserNotFoundException;
import com.healthcare.uman.mapper.UserMapper;
import com.healthcare.uman.model.medication.MedicalCard;
import com.healthcare.uman.model.medication.Medication;
import com.healthcare.uman.model.professional.HumanPreference;
import com.healthcare.uman.model.user.Patient;
import com.healthcare.uman.model.user.StatsPatient;
import com.healthcare.uman.repository.PatientRepository;

@Service
public class PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private final UserService userService;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserService userService) {
        this.patientRepository = patientRepository;
        this.userService = userService;
    }


    /**
     * Update profile's information
     *
     * @param patientDTO
     * @param id
     *
     * @throws UserNotFoundException
     */
    public void updateUser(PatientDTO patientDTO, String id) throws UserNotFoundException {
        LOGGER.debug("updateUser for user : {} with information : {}", id, patientDTO.toString());

        Optional<Patient> user = patientRepository.findById(id);
        if (!user.isPresent()) {
            LOGGER.error("No user exists with ID: {}", id);
            throw new UserNotFoundException("No user exists with the provided ID");
        }

        Patient updatedPatient = UserMapper.INSTANCE.map(patientDTO);

        if (user.isPresent()) {

            LOGGER.debug("updateUser for user : {} - user existing", id);

            Patient patientBdd = user.get();

            userService.updateUser(patientDTO, id);

            if (updatedPatient.getStatsPatient() != null) {
                updateStatsPatient(updatedPatient.getStatsPatient(), patientBdd);
                LOGGER.debug("Updated statsPatient for user ID: {}", id);
            }

            if (updatedPatient.getMedicalCard() != null) {
                updateMedicalCard(updatedPatient.getMedicalCard(), patientBdd);
                LOGGER.debug("Updated medicalCard for user ID: {}", id);
            }

            if (updatedPatient.getPreferences() != null) {
                patientBdd.setPreferences(updatePreference(updatedPatient.getPreferences(), patientBdd));
                LOGGER.debug("Updated preferences for user ID: {}", id);
            }

            if (updatedPatient.getFavoritesPro() != null) {
                patientBdd.setFavoritesPro(updatedPatient.getFavoritesPro());
                LOGGER.debug("Updated favoritesPro for user ID: {}", id);
            }

            if (updatedPatient.getBookings() != null) {
                patientBdd.setBookings(updatedPatient.getBookings());
                LOGGER.debug("Updated bookings for user ID: {}", id);
            }

            patientRepository.save(patientBdd);
            LOGGER.info("User with ID: {} updated successfully", id);
        } else {
            throw new UserNotFoundException("Aucun utilisateur n'existe");
        }
    }
    public List<HumanPreference> updatePreference(List<HumanPreference> updatedPreferences, Patient patient) {
        LOGGER.debug("Updating preferences for patient ID: {}", patient.getId());

        if (patient.getPreferences() == null) {
            patient.setPreferences(new ArrayList<>());
        }

        Map<String, HumanPreference> existingPreferencesMap = patient.getPreferences().stream()
                                                                     .collect(Collectors.toMap(pref -> pref.getName().name(), pref -> pref));

        for (HumanPreference updatedPreference : updatedPreferences) {
            if (!existingPreferencesMap.containsKey(updatedPreference.getName().name())) {
                patient.getPreferences().add(updatedPreference);
                LOGGER.debug("Added new preference: {}", updatedPreference.getName().name());
            }
            // Existing preferences are retained as is. If updates to existing preferences are required, handle them here.
        }

        // Return the updated list of preferences
        return patient.getPreferences();
    }

    public void updateMedicalCard(MedicalCard updatedMedicalCard, Patient patient) {
        LOGGER.debug("Updating medical card for patient ID: {}", patient.getId());

        if (patient.getMedicalCard() == null) {
            patient.setMedicalCard(new MedicalCard());
        }

        MedicalCard patientMedicalCard = patient.getMedicalCard();

        // Update fields if provided
        Optional.ofNullable(updatedMedicalCard.getBloodGroup()).ifPresent(patientMedicalCard::setBloodGroup);
        Optional.ofNullable(updatedMedicalCard.getDiseases()).ifPresent(patientMedicalCard::setDiseases);
        Optional.ofNullable(updatedMedicalCard.getOperations()).ifPresent(patientMedicalCard::setOperations);
        Optional.ofNullable(updatedMedicalCard.getMedications()).ifPresent(updatedMeds -> updateMedication(updatedMeds, patient));
    }

    public void updateMedication(List<Medication> updatedMedications, Patient patient) {
        LOGGER.debug("Updating medication for patient ID: {}", patient.getId());

        List<Medication> currentMedications = patient.getMedicalCard().getMedications();

        if (currentMedications == null) {
            currentMedications = new ArrayList<>();
            patient.getMedicalCard().setMedications(currentMedications);
        }

        Map<String, Medication> existingMedicationsMap = currentMedications.stream()
                                                                           .collect(Collectors.toMap(Medication::getName, medication -> medication));

        for (Medication updatedMed : updatedMedications) {
            Medication existingMed = existingMedicationsMap.get(updatedMed.getName());
            if (existingMed != null) {
                Optional.ofNullable(updatedMed.getFrequency()).ifPresent(existingMed::setFrequency);
                existingMed.setLive(updatedMed.isLive());
            } else {
                currentMedications.add(updatedMed);
            }
        }
    }

    public void updateStatsPatient(StatsPatient updatedStatsPatient, Patient patient) {
        LOGGER.debug("Updating stats patient for patient ID: {}", patient.getId());

        if (patient.getStatsPatient() == null) {
            patient.setStatsPatient(new StatsPatient());
        }

        StatsPatient patientStats = patient.getStatsPatient();

        Optional.ofNullable(updatedStatsPatient.getNbConsultation()).ifPresent(patientStats::setNbConsultation);
        Optional.ofNullable(updatedStatsPatient.getNbCancellation()).ifPresent(patientStats::setNbCancellation);
        Optional.ofNullable(updatedStatsPatient.getNbPayment()).ifPresent(patientStats::setNbPayment);
        Optional.ofNullable(updatedStatsPatient.getAmountSpend()).ifPresent(patientStats::setAmountSpend);
    }

}
