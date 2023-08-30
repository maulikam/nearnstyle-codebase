package com.revanya.apps.services.booking.service;


import com.revanya.apps.services.booking.service.repositories.BookingRepository;
import com.revanya.apps.services.salon.service.repositories.SalonRepository;
import com.revanya.apps.services.service.service.repositories.ServiceRepository;
import com.revanya.apps.services.user.service.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.revanya.apps.services.booking.dto.BookingDTO;
import com.revanya.apps.services.booking.entities.Booking;
import com.revanya.apps.services.booking.mappers.BookingMapper;
import com.revanya.apps.services.service.entities.Service;
import com.revanya.apps.services.user.entities.User;
import com.revanya.apps.services.salon.entities.Salon;

@ApplicationScoped
public class BookingService {

    @Inject
    BookingRepository bookingRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    SalonRepository salonRepository;

    @Inject
    ServiceRepository serviceRepository;


    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.INSTANCE.toEntity(bookingDTO);

        Optional<User> userOptional = userRepository.findByIdOptional(bookingDTO.getUserId());
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("User not found"));

        Optional<Salon> salonOptional = salonRepository.findByIdOptional(bookingDTO.getSalonId());
        Salon salon = salonOptional.orElseThrow(() -> new IllegalArgumentException("Salon not found"));

        List<Service> services = mapIdsToServices(bookingDTO.getServiceIds());

        booking.setUser(user);
        booking.setSalon(salon);
        booking.setServices(services);

        bookingRepository.persist(booking); // Persist the booking

        return BookingMapper.INSTANCE.toDTO(booking); // Return the DTO representation of the saved booking
    }

    @Transactional
    public BookingDTO updateBooking(Long bookingId, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findByIdOptional(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        Booking updatedBooking = BookingMapper.INSTANCE.toEntity(bookingDTO);

        // Update booking properties
        existingBooking.setBookingDate(updatedBooking.getBookingDate());
        existingBooking.setServiceDate(updatedBooking.getServiceDate());
        existingBooking.setStatus(updatedBooking.getStatus());
        existingBooking.setTotalCost(updatedBooking.getTotalCost());
        // ... other properties ...

        // Update associated services if needed
        if (bookingDTO.getServiceIds() != null) {
            List<Service> services = mapIdsToServices(bookingDTO.getServiceIds());
            existingBooking.setServices(services);
        }

        bookingRepository.persist(existingBooking); // Use persist instead of merge
        return BookingMapper.INSTANCE.toDTO(existingBooking);
    }


    @Transactional
    public void deleteBooking(Long bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findByIdOptional(bookingId);
        Booking booking = bookingOptional.orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        bookingRepository.delete(booking);
    }


    public BookingDTO getBooking(Long bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findByIdOptional(bookingId);
        Booking booking = bookingOptional.orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        return BookingMapper.INSTANCE.toDTO(booking);
    }


    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.listAll();

        return bookings.stream()
                .map(BookingMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }


    // Other methods for searching, filtering, etc.

    private List<Service> mapIdsToServices(List<Long> serviceIds) {
        if (serviceIds == null || serviceIds.isEmpty()) {
            return new ArrayList<>();
        }

        // Fetch services using the ServiceRepository
        return serviceRepository.findByIds(serviceIds);
    }
}
