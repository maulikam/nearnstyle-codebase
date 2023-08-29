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

    @Inject
    SalonMapper salonMapper; // Assuming you've created a mapper similar to the UserMapper

    public SalonDTO getSalonById(Long id) {
        return salonMapper.toDTO(salonRepository.findById(id));
    }

    public List<SalonDTO> getSalonsByName(String name) {
        return salonRepository.findByName(name).stream().map(salonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByCity(String city) {
        return salonRepository.findByCity(city).stream().map(salonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByState(String state) {
        return salonRepository.findByState(state).stream().map(salonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByCountry(String country) {
        return salonRepository.findByCountry(country).stream().map(salonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByPostalCode(String postalCode) {
        return salonRepository.findByPostalCode(postalCode).stream().map(salonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByAverageRatingAbove(Double rating) {
        return salonRepository.findByAverageRatingAbove(rating).stream().map(salonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByEmail(String email) {
        return salonRepository.findByEmail(email).stream().map(salonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByPhoneNumber(String phoneNumber) {
        return salonRepository.findByPhoneNumber(phoneNumber).stream().map(salonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByServiceTypeName(String serviceTypeName) {
        return salonRepository.findByServiceTypeName(serviceTypeName).stream().map(salonMapper::toDTO).collect(Collectors.toList());
    }

    public List<SalonDTO> getSalonsByGeoLocationId(Long locationId) {
        return salonRepository.findByGeoLocationId(locationId).stream().map(salonMapper::toDTO).collect(Collectors.toList());
    }

    // Add other methods based on your requirements...

    public void addSalon(SalonDTO salonDTO) {
        Salon salon = salonMapper.toEntity(salonDTO);
        salonRepository.persist(salon);
    }

    public void updateSalon(SalonDTO salonDTO) {
        Salon salon = salonMapper.toEntity(salonDTO);
        salonRepository.persist(salon);
    }

    public void deleteSalon(Long id) {
        salonRepository.deleteById(id);
    }
}
