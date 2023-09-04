package com.revanya.apps.services.salon.service;

import com.revanya.apps.services.salon.dto.SalonDTO;
import com.revanya.apps.services.salon.entities.Salon;

import com.revanya.apps.services.salon.mappers.SalonMapper;
import com.revanya.apps.services.salon.service.repositories.SalonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SalonService {

    @Inject
    SalonRepository salonRepository;
 
    public SalonDTO getSalonById(Long id) {
        return SalonMapper.toDTO(salonRepository.findById(id));
    }

    public List<SalonDTO> getSalonsByName(String name) {
        return salonRepository.findByName(name).stream().map(SalonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByCity(String city) {
        return salonRepository.findByCity(city).stream().map(SalonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByState(String state) {
        return salonRepository.findByState(state).stream().map(SalonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByCountry(String country) {
        return salonRepository.findByCountry(country).stream().map(SalonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByPostalCode(String postalCode) {
        return salonRepository.findByPostalCode(postalCode).stream().map(SalonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByAverageRatingAbove(Double rating) {
        return salonRepository.findByAverageRatingAbove(rating).stream().map(SalonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByEmail(String email) {
        return salonRepository.findByEmail(email).stream().map(SalonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByPhoneNumber(String phoneNumber) {
        return salonRepository.findByPhoneNumber(phoneNumber).stream().map(SalonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByServiceTypeName(String serviceTypeName) {
        return salonRepository.findByServiceTypeName(serviceTypeName).stream().map(SalonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByGeoLocationId(Long locationId) {
        return salonRepository.findByGeoLocationId(locationId).stream().map(SalonMapper::toDTO).collect(Collectors.toList());
    }

    // Add other methods based on your requirements...

    public void addSalon(SalonDTO salonDTO) {
        Salon salon = SalonMapper.toEntity(salonDTO);
        salonRepository.persist(salon);
    }

    public void updateSalon(SalonDTO salonDTO) {
        Salon salon = SalonMapper.toEntity(salonDTO);
        salonRepository.persist(salon);
    }

    public void deleteSalon(Long id) {
        salonRepository.deleteById(id);
    }
}
