package com.revanya.apps.services.booking.mappers;

import com.revanya.apps.services.booking.dto.BookingDTO;
import com.revanya.apps.services.booking.entities.Booking;
import com.revanya.apps.services.service.entities.Service;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookingMapper {

    public static BookingDTO toDTO(Booking booking) {
        if (booking == null) {
            return null;
        }

        BookingDTO dto = new BookingDTO();

        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());  // Assuming User has an ID field
        dto.setSalonId(booking.getSalon().getId());  // Assuming Salon has an ID field
        dto.setBookingDate(booking.getBookingDate());
        dto.setServiceDate(booking.getServiceDate());
        dto.setTotalCost(booking.getTotalCost());
        dto.setSpecialInstructions(booking.getSpecialInstructions());
        dto.setPaymentMode(booking.getPaymentMode());
        dto.setDiscount(booking.getDiscount());
        dto.setPromoCode(booking.getPromoCode());
        dto.setUserRating(booking.getUserRating());
        dto.setUserFeedback(booking.getUserFeedback());

        // Convert List<Service> to List<Long>
        if (booking.getServices() != null) {
            List<Long> serviceIds = booking.getServices()
                    .stream().map(Service::getId)
                    .collect(Collectors.toList());
            dto.setServiceIds(serviceIds);
        }

        return dto;
    }

    public static Booking toEntity(BookingDTO dto) {
        if (dto == null) {
            return null;
        }

        Booking booking = new Booking();

        booking.setId(dto.getId());
        // You will need to fetch User and Salon entities from database and set them here.
        // booking.setUser(user);
        // booking.setSalon(salon);
        booking.setBookingDate(dto.getBookingDate());
        booking.setServiceDate(dto.getServiceDate());
        booking.setStatus(dto.getStatus());
        booking.setTotalCost(dto.getTotalCost());
        booking.setSpecialInstructions(dto.getSpecialInstructions());
        booking.setPaymentMode(dto.getPaymentMode());
        booking.setDiscount(dto.getDiscount());
        booking.setPromoCode(dto.getPromoCode());
        booking.setUserRating(dto.getUserRating());
        booking.setUserFeedback(dto.getUserFeedback());

        // Convert List<Long> to List<Service>
        // You will need to fetch Service entities from database based on the serviceIds and set them here.
        // booking.setServices(services);

        return booking;
    }
}
