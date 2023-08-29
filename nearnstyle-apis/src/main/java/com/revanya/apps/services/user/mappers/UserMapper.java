package com.revanya.apps.services.user.mappers;

import com.revanya.apps.services.user.dto.UserDTO;
import com.revanya.apps.services.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "bookings", target = "bookingIds")
    UserDTO toDTO(User user);

    User toEntity(UserDTO dto);
}
