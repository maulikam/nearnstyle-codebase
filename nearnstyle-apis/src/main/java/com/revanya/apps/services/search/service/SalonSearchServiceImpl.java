package com.revanya.apps.services.search.service;


import com.revanya.apps.services.salon.entities.Salon;
import com.revanya.apps.services.salon.service.repositories.SalonRepository;
import com.revanya.apps.services.search.dto.SalonDetailsDTO;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SalonSearchServiceImpl implements SalonSearchService {

    // Assuming you have a SalonRepository or similar to interface with the database
    @Inject
    SalonRepository salonRepository;

    @Override
    public List<SalonDetailsDTO> searchSalonByName(String name, Page page, Sort sort) {
        List<Salon> salons = salonRepository.find("name LIKE ?1", Sort.by("name"), "%" + name + "%")
                .page(page)
                .list();
        return salons.stream()
                .map(this::mapToSalonDetailsDTO)
                .collect(Collectors.toList());
    }




    @Override
    public List<SalonDetailsDTO> searchSalonByServiceTypes(List<Long> serviceTypeIds, Page page, Sort sort) {
        // This assumes a relationship from Salon to ServiceTypes via a JoinTable or similar
        return salonRepository.find("serviceType.id IN ?1", serviceTypeIds)
                .page(page)
                .stream()
                .map(this::mapToSalonDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalonDetailsDTO> searchSalonByOperatingHours(Date from, Date to, Page page, Sort sort) {
        return salonRepository.find("openingHours <= ?1 AND closingHours >= ?2", from, to)
                .page(page)
                .stream()
                .map(this::mapToSalonDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalonDetailsDTO> searchSalonByRating(Double minRating, Page page, Sort sort) {
        return salonRepository.find("averageRating >= ?1", minRating)
                .page(page)
                .stream()
                .map(this::mapToSalonDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalonDetailsDTO> searchSalonByGeoLocation(Double latitude, Double longitude, Double radius, Page page, Sort sort) {
        // The actual implementation for this would be more complex and depends on your DB setup
        // Assuming you have some geolocation capabilities in your DB
        // This is just a basic stub
        return salonRepository.find("location within ?1", radius)
                .page(page)
                .stream()
                .map(this::mapToSalonDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SalonDetailsDTO getSalonDetailsById(Long salonId) {
        return salonRepository.findByIdOptional(salonId)
                .map(this::mapToSalonDetailsDTO)
                .orElse(null);
    }

    @Override
    public List<SalonDetailsDTO> getSalonsByUserPreferences(Long userId, Page page, Sort sort) {
        // This would depend on how user preferences are stored and how they relate to salons
        // Just a basic stub
        return salonRepository.listAll()
                .stream()
                .filter(salon -> /* filter based on user preferences */ true)
                .map(this::mapToSalonDetailsDTO)
                .collect(Collectors.toList());
    }

    private SalonDetailsDTO mapToSalonDetailsDTO(Salon salon) {
        // This method will map your Salon entity to SalonDetailsDTO
        // This is just a stub, you'd populate the fields based on your actual Salon entity
        SalonDetailsDTO dto = new SalonDetailsDTO();
        dto.setName(salon.getName());
        // ... continue for other fields
        return dto;
    }
}

