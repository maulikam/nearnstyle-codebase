package com.revanya.apps.services.geolocation.service;

import com.revanya.apps.services.geolocation.dto.GeoLocationDTO;
import com.revanya.apps.services.geolocation.entities.GeoLocation;
import com.revanya.apps.services.geolocation.mappers.GeoLocationMapper;
import com.revanya.apps.services.geolocation.service.repositories.GeoLocationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class GeoLocationService {

    @Inject
    GeoLocationRepository geoLocationRepository;

    public GeoLocationDTO createGeoLocation(GeoLocationDTO geoLocationDTO) {
        GeoLocation geoLocation = GeoLocationMapper.toEntity(geoLocationDTO);
        geoLocationRepository.persist(geoLocation);
        return GeoLocationMapper.toDTO(geoLocation);
    }


    public GeoLocationDTO updateGeoLocation(Long id, GeoLocationDTO geoLocationDTO) {
        GeoLocation existingGeoLocation = geoLocationRepository.findById(id);
        if (existingGeoLocation == null) {
            throw new IllegalArgumentException("GeoLocation not found");
        }
        GeoLocation updatedGeoLocation = GeoLocationMapper.toEntity(geoLocationDTO);
        updatedGeoLocation.setId(id);
        GeoLocation mergedGeoLocation = geoLocationRepository.getEntityManager().merge(updatedGeoLocation);
        return GeoLocationMapper.toDTO(mergedGeoLocation);
    }

    public void deleteGeoLocation(Long id) {
        GeoLocation geoLocation = geoLocationRepository.findById(id);
        if (geoLocation != null) {
            geoLocationRepository.delete(geoLocation);
        }
    }

    public GeoLocationDTO getGeoLocation(Long id) {
        GeoLocation geoLocation = geoLocationRepository.findById(id);
        if (geoLocation == null) {
            throw new IllegalArgumentException("GeoLocation not found");
        }
        return GeoLocationMapper.toDTO(geoLocation);
    }

    public List<GeoLocationDTO> getAllGeoLocations() {
        List<GeoLocation> geoLocations = geoLocationRepository.listAll();

        List<GeoLocationDTO> geoLocationDTOs = new ArrayList<>();
        for (GeoLocation geoLocation : geoLocations) {
            geoLocationDTOs.add(GeoLocationMapper.toDTO(geoLocation));
        }

        return geoLocationDTOs;
    }


    // Add more methods as needed

    // You can also add methods to find GeoLocations within a given radius,
    // based on your custom repository methods
}

