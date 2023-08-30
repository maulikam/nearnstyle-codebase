package com.revanya.apps.services.geolocation.mappers;

import com.revanya.apps.services.geolocation.dto.GeoLocationDTO;
import com.revanya.apps.services.geolocation.entities.GeoLocation;
import jakarta.inject.Singleton;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta")
@Singleton
public interface GeoLocationMapper {

    GeoLocationMapper INSTANCE = Mappers.getMapper(GeoLocationMapper.class);

    @Mapping(source = "salonsInServiceArea", target = "salonIds")
    GeoLocationDTO toDTO(GeoLocation geoLocation);

    GeoLocation toEntity(GeoLocationDTO dto);
}

