package com.revanya.apps.services.service.mappers;


import com.revanya.apps.services.service.dto.ServiceDTO;
import com.revanya.apps.services.service.entities.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServiceMapper {

    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    @Mapping(source = "booking.id", target = "bookingId")
    @Mapping(source = "serviceType.id", target = "serviceTypeId")
    ServiceDTO toDTO(Service service);

    @Mapping(source = "bookingId", target = "booking.id")
    @Mapping(source = "serviceTypeId", target = "serviceType.id")
    Service toEntity(ServiceDTO dto);

    void updateServiceFromDTO(ServiceDTO dto, @MappingTarget Service entity);

}

