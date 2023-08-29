package com.revanya.apps.services.booking.dto;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookingDTO {

    private Long id;

    private Long userId;

    private Long salonId;

    private Date bookingDate;

    private Date serviceDate;

    private BookingStatus status;

    private List<Long> serviceIds;  // Assuming you'll have some mechanism to get service details using these IDs

    private Double totalCost;

    private String specialInstructions;

    private PaymentMode paymentMode;

    private Double discount;

    private String promoCode;

    private Double userRating;

    private String userFeedback;

    public enum BookingStatus {
        PENDING, CONFIRMED, CANCELED, COMPLETED
    }

    public enum PaymentMode {
        CASH, CREDIT_CARD, DEBIT_CARD, WALLET, UPI
    }
}

