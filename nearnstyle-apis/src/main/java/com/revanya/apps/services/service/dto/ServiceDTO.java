package com.revanya.apps.services.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceDTO implements Serializable {

    private Long id;
    private Long bookingId;      // Only storing the ID here
    private Long serviceTypeId;  // Only storing the ID here
    private String serviceName;
    private Double serviceCost;
    private Integer duration;
    private String description;
    private Boolean specialEquipmentRequired;
    private Double discount;
    private Float rating;
    private String feedback;
}

