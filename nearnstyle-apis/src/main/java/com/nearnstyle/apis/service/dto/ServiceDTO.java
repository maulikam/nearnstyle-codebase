package com.nearnstyle.apis.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {

    private Long serviceId;

    private String serviceName;

    private double price;

    private String description;

    private int durationInMinutes;

    private String toolsRequired;

    private boolean isActive;

    private int maxBookingPerDay;

    private String prerequisites;
}

