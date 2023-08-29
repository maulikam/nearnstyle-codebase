package com.revanya.apps.services.geolocation.mappers;

import com.revanya.apps.services.geolocation.dto.GeoLocationDTO;
import com.revanya.apps.services.geolocation.entities.GeoLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeoLocationMapper {

    GeoLocationMapper INSTANCE = Mappers.getMapper(GeoLocationMapper.class);

    @Mapping(source = "salonsInServiceArea", target = "salonIds")
    GeoLocationDTO toDTO(GeoLocation geoLocation);

    GeoLocation toEntity(GeoLocationDTO dto);
}

