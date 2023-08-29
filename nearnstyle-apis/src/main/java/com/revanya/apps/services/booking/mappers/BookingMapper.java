package com.revanya.apps.services.booking.mappers;

import com.revanya.apps.services.booking.dto.BookingDTO;
import com.revanya.apps.services.booking.entities.Booking;
import com.revanya.apps.services.service.entities.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "salon.id", target = "salonId")
    @Mapping(source = "services", target = "serviceIds", qualifiedByName = "mapServicesToIds")
    BookingDTO toDTO(Booking booking);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "salonId", target = "salon.id")
    @Mapping(source = "serviceIds", target = "services", qualifiedByName = "mapIdsToServices")
    Booking toEntity(BookingDTO bookingDTO);

    // Custom mapper methods for service list to id list and vice versa
    default List<Long> mapServicesToIds(List<Service> services) {
        if (services == null) {
            return null;
        }
        return services.stream().map(Service::getId).collect(Collectors.toList());
    }

}

