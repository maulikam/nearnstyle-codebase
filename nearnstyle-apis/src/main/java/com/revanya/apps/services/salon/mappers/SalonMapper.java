package com.revanya.apps.services.salon.mappers;

import com.revanya.apps.services.salon.dto.SalonDTO;
import com.revanya.apps.services.salon.entities.Salon;

import java.util.stream.Collectors;

public class SalonMapper {

    public static SalonDTO toDTO(Salon salon) {
        SalonDTO dto = new SalonDTO();

        dto.setId(salon.getId());
        dto.setName(salon.getName());
        dto.setStreetAddress(salon.getStreetAddress());
        dto.setCity(salon.getCity());
        dto.setState(salon.getState());
        dto.setCountry(salon.getCountry());
        dto.setPostalCode(salon.getPostalCode());
        dto.setPhoneNumber(salon.getPhoneNumber());
        dto.setEmail(salon.getEmail());
        dto.setDescription(salon.getDescription());
        dto.setAverageRating(salon.getAverageRating());
        dto.setLocationId(salon.getLocation() != null ? salon.getLocation().getId() : null);
        dto.setServiceAreaId(salon.getServiceArea() != null ? salon.getServiceArea().getId() : null);
        dto.setOpeningHours(salon.getOpeningHours());
        dto.setClosingHours(salon.getClosingHours());
        dto.setServiceTypeIds(salon.getServiceTypes() != null ? salon.getServiceTypes().stream().map(serviceType -> serviceType.getId()).collect(Collectors.toList()) : null);
        dto.setReviewIds(salon.getReviews() != null ? salon.getReviews().stream().map(review -> review.getId()).collect(Collectors.toList()) : null);
        dto.setBookingIds(salon.getBookings() != null ? salon.getBookings().stream().map(booking -> booking.getId()).collect(Collectors.toList()) : null);

        return dto;
    }

    public static Salon toEntity(SalonDTO dto) {
        Salon salon = new Salon();

        salon.setId(dto.getId());
        salon.setName(dto.getName());
        salon.setStreetAddress(dto.getStreetAddress());
        salon.setCity(dto.getCity());
        salon.setState(dto.getState());
        salon.setCountry(dto.getCountry());
        salon.setPostalCode(dto.getPostalCode());
        salon.setPhoneNumber(dto.getPhoneNumber());
        salon.setEmail(dto.getEmail());
        salon.setDescription(dto.getDescription());
        salon.setAverageRating(dto.getAverageRating());
        salon.setOpeningHours(dto.getOpeningHours());
        salon.setClosingHours(dto.getClosingHours());

        // NOTE: Setting location, serviceArea, serviceTypes, reviews, and bookings
        // is not straightforward from the DTO. You might need additional services or methods to fetch them.
        // salon.setLocation(...);
        // salon.setServiceArea(...);
        // salon.setServiceTypes(...);
        // salon.setReviews(...);
        // salon.setBookings(...);

        return salon;
    }

    public static void updateSalonFromDTO(SalonDTO dto, Salon salon) {
        salon.setName(dto.getName());
        salon.setStreetAddress(dto.getStreetAddress());
        salon.setCity(dto.getCity());
        salon.setState(dto.getState());
        salon.setCountry(dto.getCountry());
        salon.setPostalCode(dto.getPostalCode());
        salon.setPhoneNumber(dto.getPhoneNumber());
        salon.setEmail(dto.getEmail());
        salon.setDescription(dto.getDescription());
        salon.setAverageRating(dto.getAverageRating());
        salon.setOpeningHours(dto.getOpeningHours());
        salon.setClosingHours(dto.getClosingHours());

        // NOTE: Updating location, serviceArea, serviceTypes, reviews, and bookings
        // will again require additional steps.
        // salon.setLocation(...);
        // salon.setServiceArea(...);
        // salon.setServiceTypes(...);
        // salon.setReviews(...);
        // salon.setBookings(...);
    }
}
