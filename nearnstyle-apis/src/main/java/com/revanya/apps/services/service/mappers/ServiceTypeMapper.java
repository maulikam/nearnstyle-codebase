package com.revanya.apps.services.service.mappers;

import com.revanya.apps.services.service.dto.ServiceTypeDTO;
import com.revanya.apps.services.service.entities.Service;
import com.revanya.apps.services.service.entities.ServiceType;

import java.util.stream.Collectors;

public class ServiceTypeMapper {

    public static ServiceTypeDTO toDTO(ServiceType serviceType) {
        ServiceTypeDTO dto = new ServiceTypeDTO();

        dto.setId(serviceType.getId());
        dto.setName(serviceType.getName());
        dto.setDescription(serviceType.getDescription());
        dto.setAvgDuration(serviceType.getAvgDuration());
        dto.setSpecialEquipmentRequired(serviceType.getSpecialEquipmentRequired());
        dto.setIconUrl(serviceType.getIconUrl());
        dto.setSalonId(serviceType.getSalon() != null ? serviceType.getSalon().getId() : null);
        dto.setServiceIds(serviceType.getServices() != null ? serviceType.getServices().stream().map(Service::getId).collect(Collectors.toSet()) : null);

        return dto;
    }

    public static ServiceType toEntity(ServiceTypeDTO dto) {
        ServiceType serviceType = new ServiceType();

        serviceType.setId(dto.getId());
        serviceType.setName(dto.getName());
        serviceType.setDescription(dto.getDescription());
        serviceType.setAvgDuration(dto.getAvgDuration());
        serviceType.setSpecialEquipmentRequired(dto.getSpecialEquipmentRequired());
        serviceType.setIconUrl(dto.getIconUrl());

        // NOTE: Setting salon and services is not straightforward from DTO. You might need services to fetch them.
        // serviceType.setSalon(...);
        // serviceType.setServices(...);

        return serviceType;
    }

    public static void updateServiceTypeFromDTO(ServiceTypeDTO dto, ServiceType serviceType) {
        serviceType.setName(dto.getName());
        serviceType.setDescription(dto.getDescription());
        serviceType.setAvgDuration(dto.getAvgDuration());
        serviceType.setSpecialEquipmentRequired(dto.getSpecialEquipmentRequired());
        serviceType.setIconUrl(dto.getIconUrl());

        // NOTE: Again, setting salon and services will require additional steps.
        // serviceType.setSalon(...);
        // serviceType.setServices(...);
    }
}
