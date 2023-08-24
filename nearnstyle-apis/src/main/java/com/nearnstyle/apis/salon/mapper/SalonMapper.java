package com.nearnstyle.apis.salon.mapper;

import com.nearnstyle.apis.salon.dto.SalonDTO;
import com.nearnstyle.apis.salon.model.Salon;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring", uses = {LocationMapper.class}) // Use LocationMapper for mapping Location related attributes if needed
public interface SalonMapper {

    SalonDTO toDTO(Salon salon);

    Salon fromDTO(SalonDTO salonDTO);

    List<SalonDTO> toDTOs(List<Salon> salons);

    List<Salon> fromDTOs(List<SalonDTO> salonDTOs);
}
