package com.revanya.apps.services.search.service;

import com.revanya.apps.services.search.dto.SalonDetailsDTO;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import java.util.Date;
import java.util.List;

public interface SalonSearchService {

    List<SalonDetailsDTO> searchSalonByName(String name, Page page, Sort sort);

    List<SalonDetailsDTO> searchSalonByServiceTypes(List<Long> serviceTypeIds, Page page, Sort sort);

    List<SalonDetailsDTO> searchSalonByOperatingHours(Date from, Date to, Page page, Sort sort);

    List<SalonDetailsDTO> searchSalonByRating(Double minRating, Page page, Sort sort);

    List<SalonDetailsDTO> searchSalonByGeoLocation(Double latitude, Double longitude, Double radius, Page page, Sort sort);

    SalonDetailsDTO getSalonDetailsById(Long salonId);

    List<SalonDetailsDTO> getSalonsByUserPreferences(Long userId, Page page, Sort sort);

}
