package com.revanya.apps.services.service.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class ServiceTypeDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Integer avgDuration;
    private Boolean specialEquipmentRequired;
    private String iconUrl;
    private Set<Long> serviceIds;  // Storing the IDs of services related to this type
    private Long salonId;          // Only storing the ID here
}

