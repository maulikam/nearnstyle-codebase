package com.nearnstyle.apis.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private UserType userType;
    private String profilePicture;

    public enum UserType {
        CUSTOMER, SALON_OWNER, ADMIN
    }
}
