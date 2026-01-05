package com.healthcare.uman.dto.professional;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.dto.payment.PaymentModeEnumDTO;
import com.healthcare.uman.dto.speciality.SpecialityPriceDTO;
import com.healthcare.uman.dto.user.AddressDTO;
import com.healthcare.uman.dto.user.PhotoDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessionalCardDTO implements Serializable {

    private String presentation;

    private List<LanguageDTO> languages;

    private List<SpecialityPriceDTO> specialities;

    private List<HumanPreferencesDTO> humanPreferences;

    private List<AddressDTO> professionalAddresses;

    private List<String> diplomes;

    private List<ExperienceDTO> experiences;

    private List<PaymentModeEnumDTO> paymentModeEnum;

    private List<PhotoDTO> photos;

    private boolean allowNewPatient = true;

    private List<OpeningHoursDTO> openingHours;

    private List<PricingDTO> pricings;

    public List<PricingDTO> getPricings() {
        return pricings;
    }

    public void setPricings(List<PricingDTO> pricings) {
        this.pricings = pricings;
    }

    public boolean isAllowNewPatient() {
        return allowNewPatient;
    }

    public void setAllowNewPatient(boolean allowNewPatient) {
        this.allowNewPatient = allowNewPatient;
    }

    public List<LanguageDTO> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageDTO> languages) {
        this.languages = languages;
    }

    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDTO> photos) {
        this.photos = photos;
    }

    public List<HumanPreferencesDTO> getHumanPreferences() {
        return humanPreferences;
    }

    public void setHumanPreferences(List<HumanPreferencesDTO> humanPreferences) {
        this.humanPreferences = humanPreferences;
    }

    public List<String> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(List<String> diplomes) {
        this.diplomes = diplomes;
    }

    public List<ExperienceDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceDTO> experiences) {
        this.experiences = experiences;
    }

    public List<PaymentModeEnumDTO> getPaymentModeEnum() {
        return paymentModeEnum;
    }

    public void setPaymentModeEnum(List<PaymentModeEnumDTO> paymentModeEnum) {
        this.paymentModeEnum = paymentModeEnum;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public List<OpeningHoursDTO> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(List<OpeningHoursDTO> openingHours) {
        this.openingHours = openingHours;
    }

    public List<SpecialityPriceDTO> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<SpecialityPriceDTO> specialities) {
        this.specialities = specialities;
    }

    public List<AddressDTO> getProfessionalAddresses() {
        return professionalAddresses;
    }

    public void setProfessionalAddresses(List<AddressDTO> professionalAddresses) {
        this.professionalAddresses = professionalAddresses;
    }

    public void updatePricings(List<PricingDTO> updatedPricings) {
        if (updatedPricings != null) {
            for (PricingDTO updatedPricing : updatedPricings) {
                boolean pricingUpdated = false;
                for (PricingDTO existingPricing : this.pricings) {
                    if (updatedPricing.getName().equals(existingPricing.getName())) {
                        existingPricing.setPrice(updatedPricing.getPrice());
                        pricingUpdated = true;
                        break;
                    }
                }
                if (!pricingUpdated) {
                    this.pricings.add(updatedPricing);
                }
            }
        }
    }

 /*   public void updateSpecialities(List<SpecialityDTO> updatedSpecialities) {
        if (updatedSpecialities != null) {
            for (SpecialityDTO updatedSpeciality : updatedSpecialities) {
                boolean specialityUpdated = false;
                for (SpecialityDTO existingSpeciality : this.specialities) {
                    if (updatedSpeciality.getId().equals(existingSpeciality.getId())) {
                        existingSpeciality.setName(updatedSpeciality.getName());
                        existingSpeciality.setPrice(updatedSpeciality.getPrice());
                        specialityUpdated = true;
                        break;
                    }
                }
                if (!specialityUpdated) {
                    this.specialities.add(updatedSpeciality);
                }
            }
        }
    }*/

    public void updateOpeningHours(List<OpeningHoursDTO> updatedOpeningHours) {
        if (updatedOpeningHours != null) {
            for (OpeningHoursDTO updatedSlot : updatedOpeningHours) {
                boolean slotUpdated = false;
                for (OpeningHoursDTO existingSlot : this.openingHours) {
                    if (updatedSlot.getDayEnum() == existingSlot.getDayEnum()) {
                        if (updatedSlot.getStart() != null) {
                            existingSlot.setStart(updatedSlot.getStart());
                        }
                        if (updatedSlot.getEnd() != null) {
                            existingSlot.setEnd(updatedSlot.getEnd());
                        }
                        slotUpdated = true;
                        break;
                    }
                }
                if (!slotUpdated) {
                    this.openingHours.add(updatedSlot);
                }
            }
        }
    }

    public void updateExperiences(List<ExperienceDTO> updatedExperiences) {
        if (updatedExperiences != null) {
            for (ExperienceDTO updatedExperience : updatedExperiences) {
                boolean experienceUpdated = false;
                for (ExperienceDTO existingExperience : this.experiences) {
                    if (updatedExperience.getStart().equals(existingExperience.getStart()) && updatedExperience.getEnd().equals(existingExperience.getEnd())) {
                        existingExperience.setExperience(updatedExperience.getExperience());
                        experienceUpdated = true;
                        break;
                    }
                }
                if (!experienceUpdated) {
                    this.experiences.add(updatedExperience);
                }
            }
        }
    }
/*
    public void updateHumanAbilities(List<HumanAbilitiesDTO> updateHumanAbilities) {
        if (updateHumanAbilities != null) {
            for (HumanAbilitiesDTO updatedHuman : updateHumanAbilities) {
                boolean experienceUpdated = false;
                for (HumanAbilitiesDTO existingHuman : this.humanPreferences) {
                    if (updatedHuman.getPreference().equals(existingHuman.getPreference())) {
                        existingHuman.setLevel(updatedHuman.getLevel());
                        experienceUpdated = true;
                        break;
                    }
                }
                if (!experienceUpdated) {
                    this.humanPreferences.add(updatedHuman);
                }
            }
        }
    }
*/
}
