package com.revanya.apps.services.salon.mappers;


import com.revanya.apps.services.salon.dto.SalonDTO;
import com.revanya.apps.services.salon.entities.Salon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface SalonMapper {

    SalonMapper INSTANCE = Mappers.getMapper(SalonMapper.class);

    @Mapping(source = "serviceTypes", target = "serviceTypeIds")
    @Mapping(source = "reviews", target = "reviewIds")
    @Mapping(source = "bookings", target = "bookingIds")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "serviceArea.id", target = "serviceAreaId")
    SalonDTO toDTO(Salon salon);

    // Reverse mappings if needed
    @Mapping(source = "serviceTypeIds", target = "serviceTypes")
    @Mapping(source = "reviewIds", target = "reviews")
    @Mapping(source = "bookingIds", target = "bookings")
    @Mapping(source = "locationId", target = "location.id")
    @Mapping(source = "serviceAreaId", target = "serviceArea.id")
    Salon toEntity(SalonDTO dto);

    // Add helper methods to map lists, if needed.
}

