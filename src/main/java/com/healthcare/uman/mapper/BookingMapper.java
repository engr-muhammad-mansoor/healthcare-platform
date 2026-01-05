package com.healthcare.uman.mapper;

import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.healthcare.uman.dto.booking.BookingDTO;
import com.healthcare.uman.model.booking.Booking;
import com.healthcare.uman.model.booking.BookingEnum;
import com.healthcare.uman.model.booking.BookingStatusEnum;
import com.healthcare.uman.model.user.User;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @BeforeMapping
    default void beforeMapping(@MappingTarget Booking booking, BookingDTO bookingDTO) {
        if (bookingDTO.getPatientId() != null) {
            User patient = new User();
            patient.setId(bookingDTO.getPatientId());
            booking.setPatientId(patient);
        } else {
            booking.setPatientId(null);
        }

        if (bookingDTO.getProfessionalId() != null) {
            User professional = new User();
            professional.setId(bookingDTO.getProfessionalId());
            booking.setProfessionalId(professional);
        } else {
            booking.setProfessionalId(null);
        }
    }

    @Mapping(target = "bookingType", source = "bookingType", qualifiedByName = "mapBookingEnum")
    @Mapping(target = "status", source = "status", qualifiedByName = "mapBookingStatus")
    @Mapping(source = "patientId", target = "patientId.id")
    @Mapping(source = "professionalId", target = "professionalId.id")
    @Mapping(source = "dateEnd", target = "dateEnd")
    Booking map(final BookingDTO bookingDTO);

    @Mapping(target = "patientId", source = "patientId.id")
    @Mapping(target = "professionalId", source = "professionalId.id")
    BookingDTO map(final Booking bookingDTO);

    List<Booking> map(final List<BookingDTO> bookingDTO);

    List<BookingDTO> mapBooking(final List<Booking> bookings);

    @Named("mapBookingEnum")
    default BookingEnum mapBookingEnum(final String type) {
        if (type != null) {
            switch (type) {
            case "VISIO":
                return BookingEnum.VISIO;
            case "OFFICE":
                return BookingEnum.OFFICE;
            default:
                break;
            }
            throw new IllegalArgumentException("Unexpected value '" + type + "'");
        }
        return null;
    }

    @Named("mapBookingStatus")
    default BookingStatusEnum mapBookingStatus(final String type) {
        if (type != null) {
            switch (type) {
            case "COMING":
                return BookingStatusEnum.COMING;
            case "CANCELED_BY_USER":
                return BookingStatusEnum.CANCELED_BY_USER;
            case "CANCELED_BY_PRO":
                return BookingStatusEnum.CANCELED_BY_PRO;
            case "DONE":
                return BookingStatusEnum.DONE;
            default:
                break;
            }
            throw new IllegalArgumentException("Unexpected value '" + type + "'");
        }
        return null;
    }
}
