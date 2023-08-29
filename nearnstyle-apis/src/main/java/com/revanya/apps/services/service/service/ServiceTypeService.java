package com.revanya.apps.services.service.service;


import com.revanya.apps.services.service.dto.ServiceTypeDTO;
import com.revanya.apps.services.service.entities.ServiceType;

import com.revanya.apps.services.service.mappers.ServiceTypeMapper;
import com.revanya.apps.services.service.service.repositories.ServiceTypeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServiceTypeService {

    @Inject
    ServiceTypeRepository serviceTypeRepository;

    @Inject
    ServiceTypeMapper serviceTypeMapper;

    public ServiceTypeDTO getServiceType(Long id) {
        ServiceType serviceType = serviceTypeRepository.findById(id);
        return serviceTypeMapper.toDTO(serviceType);
    }

    public List<ServiceTypeDTO> getAllServiceTypes() {
        return serviceTypeRepository.listAll().stream()
                .map(serviceTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ServiceTypeDTO createServiceType(ServiceTypeDTO serviceTypeDTO) {
        ServiceType serviceType = serviceTypeMapper.toEntity(serviceTypeDTO);
        serviceTypeRepository.persist(serviceType);
        return serviceTypeMapper.toDTO(serviceType);
    }

    public void deleteServiceType(Long id) {
        serviceTypeRepository.deleteById(id);
    }

    // ... Add other specific methods for ServiceType as required ...

}

