package com.revanya.apps.services.service.mappers;


import com.revanya.apps.services.service.dto.ServiceDTO;
import com.revanya.apps.services.service.entities.Service;

public class ServiceMapper {

    /**
     * Convert Service entity to ServiceDTO.
     *
     * @param service Service entity
     * @return ServiceDTO
     */
    public static ServiceDTO toDTO(Service service) {
        if (service == null) {
            return null;
        }

        ServiceDTO dto = new ServiceDTO();
        dto.setId(service.getId());
        dto.setBookingId(service.getBooking() != null ? service.getBooking().getId() : null);
        dto.setServiceTypeId(service.getServiceType() != null ? service.getServiceType().getId() : null);
        dto.setServiceName(service.getServiceName());
        dto.setServiceCost(service.getServiceCost());
        dto.setDuration(service.getDuration());
        dto.setDescription(service.getDescription());
        dto.setSpecialEquipmentRequired(service.getSpecialEquipmentRequired());
        dto.setDiscount(service.getDiscount());
        dto.setRating(service.getRating());
        dto.setFeedback(service.getFeedback());

        return dto;
    }

    /**
     * Convert ServiceDTO to Service entity.
     *
     * @param dto ServiceDTO
     * @return Service
     */
    public static Service toEntity(ServiceDTO dto) {
        if (dto == null) {
            return null;
        }

        Service service = new Service();
        service.setId(dto.getId());
        // Note: Setting of Booking and ServiceType entities are skipped here. Should be handled in your service layer.
        service.setServiceName(dto.getServiceName());
        service.setServiceCost(dto.getServiceCost());
        service.setDuration(dto.getDuration());
        service.setDescription(dto.getDescription());
        service.setSpecialEquipmentRequired(dto.getSpecialEquipmentRequired());
        service.setDiscount(dto.getDiscount());
        service.setRating(dto.getRating());
        service.setFeedback(dto.getFeedback());

        return service;
    }

    public static void updateServiceFromDTO(ServiceDTO dto, Service service) {
        // Update properties
        service.setServiceName(dto.getServiceName());
        service.setServiceCost(dto.getServiceCost());
        service.setDuration(dto.getDuration());
        service.setDescription(dto.getDescription());
        service.setSpecialEquipmentRequired(dto.getSpecialEquipmentRequired());
        service.setDiscount(dto.getDiscount());
        service.setRating(dto.getRating());
        service.setFeedback(dto.getFeedback());

        // NOTE: Booking and ServiceType are skipped here.
        // You may need to fetch and set them based on the DTO's IDs depending on your requirements.
    }
}
