package com.healthcare.uman.mapper;

import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.healthcare.uman.dto.payment.PaymentDTO;
import com.healthcare.uman.model.payment.Payment;
import com.healthcare.uman.model.user.User;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @BeforeMapping
    default void beforeMapping(@MappingTarget Payment payment, PaymentDTO paymentDTO) {
        User patient = new User();
        patient.setId(paymentDTO.getId());
        payment.setUser(patient);
    }

    @Mapping(source = "id", target = "user.id")
    Payment map(final PaymentDTO paymentDTO);

    @Mapping(target = "user", source = "user.id")
    PaymentDTO map(final Payment payment);

    List<PaymentDTO> map(List<Payment> payments);

}
