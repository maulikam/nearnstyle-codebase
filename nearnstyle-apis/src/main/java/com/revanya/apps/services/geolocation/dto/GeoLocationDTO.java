package com.revanya.apps.services.geolocation.dto;

import lombok.Data;

@Data
public class GeoLocationDTO {

    private Long id;
    private Double latitude;
    private Double longitude;
    private String description;
    private Double radius; // We include radius in the DTO for service definition

    // Getters and setters if needed
}

