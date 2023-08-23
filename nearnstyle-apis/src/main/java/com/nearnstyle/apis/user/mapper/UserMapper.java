package com.nearnstyle.apis.user.mapper;

import com.nearnstyle.apis.salon.model.Salon;
import com.nearnstyle.apis.role.model.Role;
import com.nearnstyle.apis.user.dto.UserDTO;
import com.nearnstyle.apis.user.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class UserMapper {

    private UserMapper() {
    }

    /**
     * Converts user dto to user modal
     *
     * @param userDto An instance of UserDto
     * @return An instance of Use
     */
    public static User convertUserDtoToMaster(UserDTO userDto, User user) {

        user.setUserName(userDto.getUserName());

        user.setRoleId(userDto.getRoleId());
        user.setOrgId(userDto.getOrgId());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setEmailId(userDto.getEmailId());
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setGoogleId(userDto.getGoogleId());

        if (StringUtils.isEmpty(user.getFirstName())
                && StringUtils.isEmpty(user.getLastName())) {
            if (!StringUtils.isEmpty(userDto.getName())) {
                String[] names = userDto.getName().split("\\s+", 2);
                if (names.length > 0) {
                    user.setFirstName(names[0]);
                }
                if (names.length > 1) {
                    user.setLastName(names[1]);
                }
            }
        }

        user.setGender(userDto.getGender());
        user.setState(userDto.getState());
        user.setNoOfAttempts(userDto.getNoOfAttempts());
        user.setPassword(userDto.getPassword());
        user.setAddress(userDto.getAddress());
        user.setId(userDto.getId());

        if (user.getUserName() == null) {
            user.setUserName(userDto.getMobileNumber());
        }

        return user;
    }


    /**
     * Converts user modal to user dto
     *
     * @param user An instance of User
     * @return An instance of UserDto
     */
    public static UserDTO convertUserMasterToDto(User user) {

        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setRoleId(user.getRoleId());
        userDto.setOrgId(user.getOrgId());
        userDto.setGoogleId(user.getGoogleId());

        // Check if the user's role is not null before getting the role's name
        if (user.getRole() != null) {
            userDto.setRoleName(user.getRole().getName());
        }
        userDto.setMobileNumber(user.getMobileNumber());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setEmailId(user.getEmailId());
        userDto.setAddress(user.getAddress());
        userDto.setName(user.getFirstName() + " " + user.getLastName());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setGender(user.getGender());
        userDto.setState(user.getState());
        userDto.setNoOfAttempts(user.getNoOfAttempts());
        userDto.setCreatedBy(user.getCreatedBy());
        userDto.setCreatedOn(user.getCreatedOn());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    /**
     * Converts a list of user modal to user dto
     *
     * @param userMasterList A list of User
     * @return A list of UserDto
     */
    public static List<UserDTO> convertUserMasterListToDtoList(List<User> userMasterList) {
        LinkedList<UserDTO> userDTOList = new LinkedList<>();
        for (User userMaster : userMasterList) {
            userDTOList.push(convertUserMasterToDto(userMaster));
        }
        return userDTOList;
    }
}
