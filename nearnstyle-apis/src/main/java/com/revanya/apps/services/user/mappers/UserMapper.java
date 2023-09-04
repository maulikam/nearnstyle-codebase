package com.revanya.apps.services.user.mappers;

import com.revanya.apps.services.user.dto.UserDTO;
import com.revanya.apps.services.user.entities.User;
import java.util.stream.Collectors;

public class UserMapper {

    /**
     * Converts a User entity to UserDTO.
     *
     * @param user User entity
     * @return UserDTO
     */
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setAddress(toAddressDTO(user.getAddress()));
        dto.setPreferences(user.getPreferences());
        dto.setProfilePictureURL(user.getProfilePictureURL());
        dto.setRegistrationDate(user.getRegistrationDate());
        dto.setLastLoginDate(user.getLastLoginDate());
        dto.setBookingIds(user.getBookings().stream().map(booking -> booking.getId()).collect(Collectors.toList()));
        dto.setLocationId(user.getLocation() != null ? user.getLocation().getId() : null);
        dto.setRoleNames(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));

        return dto;
    }

    /**
     * Converts a UserDTO to User entity.
     *
     * @param dto UserDTO
     * @return User
     */
    public static User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setMobileNumber(dto.getMobileNumber());
        user.setAddress(toAddressEntity(dto.getAddress()));
        user.setPreferences(dto.getPreferences());
        user.setProfilePictureURL(dto.getProfilePictureURL());
        user.setRegistrationDate(dto.getRegistrationDate());
        user.setLastLoginDate(dto.getLastLoginDate());
        // Note: Location and Roles would need further processing if necessary

        return user;
    }

    /**
     * Converts embedded Address entity to AddressDTO.
     *
     * @param address Address entity
     * @return AddressDTO
     */
    private static UserDTO.AddressDTO toAddressDTO(User.Address address) {
        if (address == null) {
            return null;
        }

        UserDTO.AddressDTO dto = new UserDTO.AddressDTO();
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZip(address.getZip());
        dto.setCountry(address.getCountry());

        return dto;
    }

    /**
     * Converts AddressDTO to embedded Address entity.
     *
     * @param dto AddressDTO
     * @return Address entity
     */
    private static User.Address toAddressEntity(UserDTO.AddressDTO dto) {
        if (dto == null) {
            return null;
        }

        User.Address address = new User.Address();
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZip(dto.getZip());
        address.setCountry(dto.getCountry());

        return address;
    }

}
