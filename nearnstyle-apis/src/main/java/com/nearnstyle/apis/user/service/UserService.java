package com.nearnstyle.apis.user.service;
 
import com.nearnstyle.apis.user.dto.UserDTO;
import com.nearnstyle.apis.user.model.User;

import java.util.List;

public interface UserService {



    /**
     * Create a new user
     *
     * @param userName        Unique identifier for the user
     * @param userDTO   DTO object containing user details
     * @return UserDTO  DTO object of created user
     */
    UserDTO createUser(String userName, UserDTO userDTO);

    /**
     * Create a new user
     * @param userDTO
     * @return
     */
    UserDTO registerUser(UserDTO userDTO);

    /**
     * Update an existing user
     *
     * @param userName        Unique identifier for the user
     * @param userDTO   DTO object containing updated user details
     * @return UserDTO  DTO object of updated user
     */
    UserDTO updateUser(String userName, UserDTO userDTO);

    /**
     * Retrieve a user by its unique identifier
     *
     * @param id    Unique identifier for the user
     * @return UserDTO  DTO object of user
     */
    UserDTO getUserById(Long id);

    /**
     * Get a user by username.
     *
     * @param username String username of the user
     * @return UserDTO object containing user details
     */
    UserDTO getUserByUsername(String username);

    UserDTO getUserByGoogleId(String googleId);


    /**
     * Get a user by Mobile Number
     * @param mobilenumber  String mobilenumber of the user
     * @return UserDTO object containing user details
     */
    UserDTO getUserByMoblieNumber(String mobilenumber);


    /**
     * Retrieve list of all users
     *
     * @return List<UserDTO>  List of DTO objects of all users
     */
    List<UserDTO> getAllUsers();
    List<User> getUsersByOrgId(Long organisationId);
}
