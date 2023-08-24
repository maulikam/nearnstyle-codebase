package com.nearnstyle.apis.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalonDTO {

    private Long salonId;

    private String salonName;

    private String address;

    private String phoneNumber;

    private String description;

    private boolean isActive;

    private String operatingHours;

    private String facilities;

    private String ownerName;

    private Long locationId; // reference to the location by its ID
}
