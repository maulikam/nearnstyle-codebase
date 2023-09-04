package com.revanya.apps.services.geolocation.mappers;

import com.revanya.apps.services.geolocation.dto.GeoLocationDTO;
import com.revanya.apps.services.geolocation.entities.GeoLocation;

public class GeoLocationMapper {

    // Convert GeoLocation entity to GeoLocationDTO
    public static GeoLocationDTO toDTO(GeoLocation geoLocation) {
        if (geoLocation == null) {
            return null;
        }

        GeoLocationDTO dto = new GeoLocationDTO();
        dto.setId(geoLocation.getId());
        dto.setLatitude(geoLocation.getLatitude());
        dto.setLongitude(geoLocation.getLongitude());
        dto.setDescription(geoLocation.getDescription());
        dto.setRadius(geoLocation.getRadius());

        return dto;
    }

    // Convert GeoLocationDTO to GeoLocation entity
    public static GeoLocation toEntity(GeoLocationDTO dto) {
        if (dto == null) {
            return null;
        }

        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setId(dto.getId());
        geoLocation.setLatitude(dto.getLatitude());
        geoLocation.setLongitude(dto.getLongitude());
        geoLocation.setDescription(dto.getDescription());
        geoLocation.setRadius(dto.getRadius());

        return geoLocation;
    }
}

