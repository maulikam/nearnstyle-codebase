package com.revanya.apps.services.search.dto;


import com.revanya.apps.services.review.dto.ReviewDTO;
import com.revanya.apps.services.service.dto.ServiceTypeDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalonDetailsDTO {

    // Salon Name
    private String name;

    // Address
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    // Ratings & Reviews
    private Double averageRating; // Assuming the average rating is pre-calculated for performance
    private List<ReviewDTO> reviews; // A simple DTO for review details

    // Operating Hours
    private String openingHours;
    private String closingHours;

    // Contact Information
    private String phoneNumber;
    private String email;

    // List of Services Offered
    private List<ServiceTypeDTO> serviceTypes; // A simple DTO for service types

}

