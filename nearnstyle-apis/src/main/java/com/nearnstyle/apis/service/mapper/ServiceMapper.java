package com.nearnstyle.apis.service.mapper;

import com.nearnstyle.apis.service.dto.ServiceDTO;
import com.nearnstyle.apis.service.model.Service;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDTO toDTO(Service service);

    Service fromDTO(ServiceDTO serviceDTO);

    List<ServiceDTO> toDTOs(List<Service> services);

    List<Service> fromDTOs(List<ServiceDTO> serviceDTOs);
}

