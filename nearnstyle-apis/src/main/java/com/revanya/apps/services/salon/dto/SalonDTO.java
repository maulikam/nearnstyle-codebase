package com.revanya.apps.services.salon.dto;


import lombok.Data;

import java.util.List;

@Data
public class SalonDTO {

    private Long id;
    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phoneNumber;
    private String email;
    private String description;
    private Double averageRating;
    private Long locationId; // Assuming only the ID is required for DTO
    private Long serviceAreaId; // Assuming only the ID is required for DTO
    private String openingHours;
    private String closingHours;
    private List<Long> serviceTypeIds; // Only storing the IDs here
    private List<Long> reviewIds; // Only storing the IDs here
    private List<Long> bookingIds; // Only storing the IDs here

}

