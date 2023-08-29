package com.revanya.apps.services.salon.service;


import com.revanya.apps.services.salon.entities.Salon;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SalonRepository implements PanacheRepository<Salon> {

    public List<Salon> findByName(String name) {
        return list("name", name);
    }

    public List<Salon> findByCity(String city) {
        return list("city", city);
    }

    public List<Salon> findByState(String state) {
        return list("state", state);
    }

    public List<Salon> findByCountry(String country) {
        return list("country", country);
    }

    public List<Salon> findByPostalCode(String postalCode) {
        return list("postalCode", postalCode);
    }

    public List<Salon> findByAverageRatingAbove(Double rating) {
        return list("averageRating > ?1", rating);
    }

    public List<Salon> findByEmail(String email) {
        return list("email", email);
    }

    public List<Salon> findByPhoneNumber(String phoneNumber) {
        return list("phoneNumber", phoneNumber);
    }

    // This method returns salons with a specific service type.
    // Note: This uses a JOIN FETCH query since ServiceType is in a bidirectional relationship with Salon.
    public List<Salon> findByServiceTypeName(String serviceTypeName) {
        return list("SELECT s FROM Salon s JOIN FETCH s.serviceTypes st WHERE st.name = ?1", serviceTypeName);
    }

    // This method returns salons within a specific geolocation
    public List<Salon> findByGeoLocationId(Long locationId) {
        return list("location.id", locationId);
    }

    // And so on... you can continue to add custom queries related to the attributes and relationships of Salon.

}


