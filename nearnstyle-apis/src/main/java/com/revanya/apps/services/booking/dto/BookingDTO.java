package com.revanya.apps.services.booking.dto;


import com.revanya.apps.services.booking.entities.Booking;
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

    private Booking.BookingStatus status;

    private List<Long> serviceIds;  // Assuming you'll have some mechanism to get service details using these IDs

    private Double totalCost;

    private String specialInstructions;

    private Booking.PaymentMode paymentMode;

    private Double discount;

    private String promoCode;

    private Double userRating;

    private String userFeedback;


}

