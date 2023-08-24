package com.nearnstyle.apis.salon.mapper;

import com.nearnstyle.apis.salon.dto.LocationDTO;
import com.nearnstyle.apis.salon.model.Location;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDTO toDTO(Location location);

    Location fromDTO(LocationDTO locationDTO);

    List<LocationDTO> toDTOs(List<Location> locations);

    List<Location> fromDTOs(List<LocationDTO> locationDTOs);
}

