package com.nearnstyle.apis.salon.mapper;

import com.nearnstyle.apis.salon.dto.OperatingHoursDTO;
import com.nearnstyle.apis.salon.model.OperatingHours;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperatingHoursMapper {

    OperatingHoursDTO toDTO(OperatingHours operatingHours);

    OperatingHours fromDTO(OperatingHoursDTO operatingHoursDTO);

    List<OperatingHoursDTO> toDTOs(List<OperatingHours> operatingHours);

    List<OperatingHours> fromDTOs(List<OperatingHoursDTO> operatingHoursDTOs);
}

