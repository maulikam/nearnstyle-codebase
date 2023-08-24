package com.nearnstyle.apis.payment.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long paymentId;

    private Long userId;  // reference to the user by its ID

    private Long salonId; // reference to the salon by its ID

    private BigDecimal amount;

    private LocalDateTime dateTime;

    private PaymentMethod paymentMethod;

    private PaymentStatus status;

    public enum PaymentMethod {
        CREDIT_CARD, IN_APP_WALLET, UPI, NETBANKING, GATEWAY
    }

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
}

