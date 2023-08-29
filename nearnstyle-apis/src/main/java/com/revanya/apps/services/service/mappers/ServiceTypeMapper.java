package com.revanya.apps.services.service.mappers;

import com.revanya.apps.services.service.dto.ServiceTypeDTO;
import com.revanya.apps.services.service.entities.Service;
import com.revanya.apps.services.service.entities.ServiceType;
import com.revanya.apps.services.service.service.ServiceService;
import jakarta.inject.Inject;
import org.mapstruct.Mapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(uses = {ServiceMapper.class})
public abstract class ServiceTypeMapper {

    @Inject
    ServiceService serviceService;  // Inject the service

    public abstract ServiceTypeDTO toDTO(ServiceType serviceType);

    public abstract ServiceType toEntity(ServiceTypeDTO dto);

    @AfterMapping
    protected void toDTOAfterMapping(ServiceType serviceType, @MappingTarget ServiceTypeDTO dto) {
        if (serviceType.getServices() != null) {
            dto.setServiceIds(serviceType.getServices().stream()
                    .map(Service::getId)
                    .collect(Collectors.toSet()));
        }
    }

    @AfterMapping
    protected void toEntityAfterMapping(ServiceTypeDTO dto, @MappingTarget ServiceType serviceType) {
        if (dto.getServiceIds() != null) {
            List<Long> serviceIds = new ArrayList<>(dto.getServiceIds());
            List<Service> services = serviceService.findServicesByIds(serviceIds);
            serviceType.setServices(new HashSet<>(services));
        }
    }

}

