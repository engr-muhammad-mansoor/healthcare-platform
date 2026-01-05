package com.healthcare.uman.model.professional;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthcare.uman.model.payment.PaymentModeEnum;
import com.healthcare.uman.model.speciality.SpecialityPrice;
import com.healthcare.uman.model.user.Address;
import com.healthcare.uman.model.user.Photo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessionalCard implements Serializable {

    private String presentation;

    private List<Language> languages;

    private List<SpecialityPrice> specialities;

    @DBRef
    private List<HumanPreference> humanPreferences;

    private List<Address> professionalAddresses;

    private List<String> diplomes;

    private List<Experience> experiences;

    private List<PaymentModeEnum> paymentModeEnum;

    private List<Photo> photos;

    private boolean allowNewPatient = true;

    private List<OpeningHours> openingHours;

    private List<Pricing> pricings;

    public List<Pricing> getPricings() {
        return pricings;
    }

    public void setPricings(List<Pricing> pricings) {
        this.pricings = pricings;
    }

    public boolean isAllowNewPatient() {
        return allowNewPatient;
    }

    public void setAllowNewPatient(boolean allowNewPatient) {
        this.allowNewPatient = allowNewPatient;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<HumanPreference> getHumanPreferences() {
        return humanPreferences;
    }

    public void setHumanPreferences(List<HumanPreference> humanPreferences) {
        this.humanPreferences = humanPreferences;
    }

    public List<String> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(List<String> diplomes) {
        this.diplomes = diplomes;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<PaymentModeEnum> getPaymentModeEnum() {
        return paymentModeEnum;
    }

    public void setPaymentModeEnum(List<PaymentModeEnum> paymentModeEnum) {
        this.paymentModeEnum = paymentModeEnum;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public List<OpeningHours> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(List<OpeningHours> openingHours) {
        this.openingHours = openingHours;
    }

    public List<SpecialityPrice> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<SpecialityPrice> specialities) {
        this.specialities = specialities;
    }

    public List<Address> getProfessionalAddresses() {
        return professionalAddresses;
    }

    public void setProfessionalAddresses(List<Address> professionalAddresses) {
        this.professionalAddresses = professionalAddresses;
    }

    public void updatePricings(List<Pricing> updatedPricings) {
        if (updatedPricings != null) {
            for (Pricing updatedPricing : updatedPricings) {
                boolean pricingUpdated = false;
                for (Pricing existingPricing : this.pricings) {
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

    public void updateSpecialities(List<SpecialityPrice> updatedSpecialities) {
        if (updatedSpecialities != null) {
            for (SpecialityPrice updatedSpeciality : updatedSpecialities) {
                boolean specialityUpdated = false;
                for (SpecialityPrice existingSpeciality : this.specialities) {
                    if (updatedSpeciality.getSpeciality().getId().equals(existingSpeciality.getSpeciality().getId())) {
                        existingSpeciality.getSpeciality().setName(updatedSpeciality.getSpeciality().getName());
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
    }

    public void updateOpeningHours(List<OpeningHours> updatedOpeningHours) {
        if (updatedOpeningHours != null) {
            for (OpeningHours updatedSlot : updatedOpeningHours) {
                boolean slotUpdated = false;
                for (OpeningHours existingSlot : this.openingHours) {
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

    public void updateExperiences(List<Experience> updatedExperiences) {
        if (updatedExperiences != null) {
            for (Experience updatedExperience : updatedExperiences) {
                boolean experienceUpdated = false;
                for (Experience existingExperience : this.experiences) {
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
    public void updateHumanAbilities(List<HumanAbilities> updateHumanAbilities) {
        if (updateHumanAbilities != null) {
            for (HumanAbilities updatedHuman : updateHumanAbilities) {
                boolean experienceUpdated = false;
                for (HumanAbilities existingHuman : this.humanAbilities) {
                    if (updatedHuman.getPreference().equals(existingHuman.getPreference())) {
                        existingHuman.setLevel(updatedHuman.getLevel());
                        experienceUpdated = true;
                        break;
                    }
                }
                if (!experienceUpdated) {
                    this.humanAbilities.add(updatedHuman);
                }
            }
        }
    }*/

}
