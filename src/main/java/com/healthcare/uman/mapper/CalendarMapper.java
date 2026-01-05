package com.healthcare.uman.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.healthcare.uman.dto.booking.CalendarDTO;
import com.healthcare.uman.model.booking.Calendar;
import com.healthcare.uman.model.user.User;

@Mapper
public interface CalendarMapper {

    CalendarMapper INSTANCE = Mappers.getMapper(CalendarMapper.class);

    @BeforeMapping
    default void beforeMapping(@MappingTarget Calendar calendar, CalendarDTO calendarDTO) {
        User patient = new User();
        patient.setId(calendarDTO.getIdUser());
        calendar.setIdUser(patient);
    }

    @Mapping(source = "idUser", target = "idUser.id")
    Calendar map(final CalendarDTO calendarDTO);

    @Mapping(source = "idUser.id", target = "idUser")
    CalendarDTO map(final Calendar calendar);

    default User map(String id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    default String map(User user) {
        return user.getId();
    }
}
