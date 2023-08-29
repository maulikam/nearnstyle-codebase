package com.revanya.apps.services.geolocation.service.repositories;

import com.revanya.apps.services.geolocation.entities.GeoLocation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class GeoLocationRepository implements PanacheRepository<GeoLocation> {
    // You can add custom query methods here if needed
}

