package com.nearnstyle.apis.user.dto;

import com.nearnstyle.apis.user.model.User;
import lombok.Data;

import java.util.Date;

/**
 * User Type Dto
 */
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String googleId;
    private String password;
    private Long roleId;
    private Long roleBasedId;
    private Long orgId;
    private String roleName;
    private String gender;
    private String address;
    private Date DateOfBirth;
    private Integer noOfAttempts;
    private String mobileNumber;
    private String emailId;
    private User.State state;
    private Date createdOn;
    private Long createdBy;

}
