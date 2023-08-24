package com.nearnstyle.apis.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateUpdateDTO {

    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private UserType userType;

    public enum UserType {
        CUSTOMER, SALON_OWNER, ADMIN
    }
}

