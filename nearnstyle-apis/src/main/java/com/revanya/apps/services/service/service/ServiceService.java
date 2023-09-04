package com.revanya.apps.services.service.service;


import com.revanya.apps.services.service.dto.ServiceDTO;
import com.revanya.apps.services.service.entities.Service;
import com.revanya.apps.services.service.mappers.ServiceMapper;
import com.revanya.apps.services.service.service.repositories.ServiceRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServiceService {

    @Inject
    ServiceRepository serviceRepository;

    public ServiceDTO getService(Long id) {
        Service service = serviceRepository.findById(id);
        return ServiceMapper.toDTO(service);
    }

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.listAll().stream()
                .map(ServiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ServiceDTO createService(ServiceDTO serviceDTO) {
        Service service = ServiceMapper.toEntity(serviceDTO);
        serviceRepository.persist(service);
        return ServiceMapper.toDTO(service);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    public List<Service> findServicesByIds(List<Long> ids) {
        return serviceRepository.findByIds(ids);
    }

    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {
        // Fetch the service based on the ID
        Service service = serviceRepository.findById(id);
        if (service == null) {
            throw new EntityNotFoundException("Service with ID " + id + " not found");
        }

        // Map the serviceDTO to the service entity and save
        ServiceMapper.updateServiceFromDTO(serviceDTO, service);
        serviceRepository.persist(service);

        // Return the updated service as a DTO
        return ServiceMapper.toDTO(service);
    }

    public List<ServiceDTO> searchServicesByName(String name) {
        // Search services by name
        List<Service> services = serviceRepository.findByName(name);

        // Convert the list of service entities to DTOs
        return services.stream().map(ServiceMapper::toDTO).collect(Collectors.toList());
    }

    public List<ServiceDTO> getServicesAboveRating(Float threshold) {
        // Fetch services above a particular rating
        List<Service> services = serviceRepository.findByRatingAbove(threshold);

        // Convert the list of service entities to DTOs
        return services.stream().map(ServiceMapper::toDTO).collect(Collectors.toList());
    }


    public List<ServiceDTO> getServicesByDuration(Integer duration) {
        // Fetch services of a specific duration
        List<Service> services = serviceRepository.findByDuration(duration);

        // Convert the list of service entities to DTOs
        return services.stream().map(ServiceMapper::toDTO).collect(Collectors.toList());
    }




    // ... Add other specific methods for Service as required ...

}

