package com.nearnstyle.apis.payment.mapper;

import com.nearnstyle.apis.payment.dto.PaymentDTO;
import com.nearnstyle.apis.payment.model.Payment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDTO toDTO(Payment payment);

    Payment fromDTO(PaymentDTO paymentDTO);

    List<PaymentDTO> toDTOs(List<Payment> payments);

    List<Payment> fromDTOs(List<PaymentDTO> paymentDTOs);
}

