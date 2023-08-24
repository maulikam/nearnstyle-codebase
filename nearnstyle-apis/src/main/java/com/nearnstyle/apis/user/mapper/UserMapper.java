package com.nearnstyle.apis.user.mapper;

import com.nearnstyle.apis.user.dto.UserCreateUpdateDTO;
import com.nearnstyle.apis.user.dto.UserDTO;
import com.nearnstyle.apis.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    User fromDTO(UserCreateUpdateDTO userCreateUpdateDTO);

    List<UserDTO> toDTOs(List<User> users);
    List<User> fromDTOs(List<UserCreateUpdateDTO> userDTOs);

    // If updating, you might want an additional method to merge changes
    void updateFromDTO(UserCreateUpdateDTO userCreateUpdateDTO, @MappingTarget User user);
}
