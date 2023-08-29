package com.revanya.apps.services.booking.service.repositories;

import com.revanya.apps.services.booking.entities.Booking;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class BookingRepository implements PanacheRepositoryBase<Booking, Long> {
    // You can add custom query methods here if needed
}

