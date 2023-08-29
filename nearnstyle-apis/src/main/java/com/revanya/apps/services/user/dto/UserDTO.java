package com.revanya.apps.services.user.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String mobileNumber;
    private AddressDTO address;
    private Map<String, String> preferences;
    private String profilePictureURL;
    private Date registrationDate;
    private Date lastLoginDate;
    private List<Long> bookingIds; // Only storing the IDs here
    private Long locationId; // Assuming only the ID is required for DTO
    private List<String> roleNames; // List of role names

    @Data
    public static class AddressDTO {
        private String street;
        private String city;
        private String state;
        private String zip;
        private String country;
    }
}

