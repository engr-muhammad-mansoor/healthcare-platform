package com.healthcare.uman.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.healthcare.uman.dto.booking.CalendarDTO;
import com.healthcare.uman.dto.user.PatientDTO;
import com.healthcare.uman.dto.user.ProfessionalDTO;
import com.healthcare.uman.dto.user.UserDTO;
import com.healthcare.uman.model.booking.Calendar;
import com.healthcare.uman.model.connexion.UserRegistrationRequest;
import com.healthcare.uman.model.user.Patient;
import com.healthcare.uman.model.user.Professional;
import com.healthcare.uman.model.user.User;
import com.healthcare.uman.model.user.UserEnum;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Professional map(ProfessionalDTO professionalDTO);

    ProfessionalDTO map(final Professional professional);

    List<ProfessionalDTO> map(final List<Professional> professional);

    Patient map(final PatientDTO patientDTO);

    PatientDTO map(final Patient patient);
    Calendar map(final CalendarDTO calendarDTO);
    CalendarDTO map(final Calendar calendar);

    User map(final UserDTO userDTO);

    UserDTO mapUserDto(final User user);

    @Mapping(target = "type", source = "type", qualifiedByName = "mapEnum")
    User map(final UserRegistrationRequest userRegistrationRequest);

    @Mapping(target = "type", source = "type", qualifiedByName = "mapEnum")
    Patient mapPatient(final UserRegistrationRequest userRegistrationRequest);

    @Named("mapEnum")
    default UserEnum mapEnum(final String type) {

        if (type != null) {
            switch (type) {
            case "PATIENT":
                return UserEnum.PATIENT;
            case "PRO":
                return UserEnum.PRO;
            default:
                break;
            }

            throw new IllegalArgumentException("Unexpected value '" + type + "'");
        }
        return null;

    }

    default User map(String id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    default String map(User user) {
        return user.getId();
    }

}
