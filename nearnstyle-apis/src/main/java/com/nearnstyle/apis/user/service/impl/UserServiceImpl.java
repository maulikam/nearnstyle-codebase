package com.nearnstyle.apis.user.service.impl;
 
import com.nearnstyle.apis.salon.service.SalonService; 
import com.nearnstyle.apis.role.dto.RoleDTO;
import com.nearnstyle.apis.role.service.RoleService;
import com.nearnstyle.apis.user.repository.UserRepository;
import com.nearnstyle.apis.user.dto.UserDTO;
import com.nearnstyle.apis.user.mapper.UserMapper;
import com.nearnstyle.apis.user.model.User;
import com.nearnstyle.apis.user.service.UserService;
import java.util.List;
import java.util.Optional;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalonService salonService;
 

    @Autowired
    RoleService roleService;

    private BasicPasswordEncryptor basicPasswordEncryptor = new BasicPasswordEncryptor();

    /**
     * Get user details by username
     *
     * @param userName Username of the user
     * @return userDto with user details
     */
    @Override
    public UserDTO getUserByUsername(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        return userOptional.map(UserMapper::convertUserMasterToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for userName: " + userName));
    }

    @Override
    public UserDTO getUserByGoogleId(String googleId) {
        Optional<User> userOptional = userRepository.findByGoogleId(googleId);
        UserDTO userDTO;
        if (userOptional.isPresent()) {
            userDTO = UserMapper.convertUserMasterToDto(userOptional.get());
        } else {
            throw new ResourceNotFoundException("User not found for googleId: " + googleId);
        }
 
        return userDTO;
    }

    @Override
    public UserDTO getUserByMoblieNumber(String mobilenumber) {
        Optional<User> users = userRepository.findByMobileNumber(mobilenumber);
        UserDTO userDTO;
        if (users.isPresent()) {
            userDTO = UserMapper.convertUserMasterToDto(users.get());
        } else {
            throw new ResourceNotFoundException("User not found for mobileNumber: " + mobilenumber);
        }
 
        return userDTO;

    }




    /**
     * Create or update user
     *
     * @param userDto An instance of UserDto
     */
    @Override
    public UserDTO createUser(String userName, UserDTO userDto) {

        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isPresent()) {
            throw new IllegalStateException("User already exists");
        }

        User user = UserMapper.convertUserDtoToMaster(userDto, new User());
        user.setPassword(basicPasswordEncryptor.encryptPassword(userDto.getPassword()));
        return UserMapper.convertUserMasterToDto(userRepository.save(user));
    }

     
    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        // Check for existing mobile number
        Optional<User> userOptional = userRepository.findByMobileNumber(userDTO.getMobileNumber());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("User already exists with this mobile number: " + userDTO.getMobileNumber());
        }

        // Check for existing email ID (only if email is provided in the DTO)
        if (userDTO.getEmailId() != null && !userDTO.getEmailId().isEmpty()) {
            Optional<User> userWithEmail = userRepository.findByEmailId(userDTO.getEmailId());
            if (userWithEmail.isPresent()) {
                throw new IllegalStateException("User already exists with this email ID: " + userDTO.getEmailId());
            }
        }
        User user = UserMapper.convertUserDtoToMaster(userDTO, new User());
        user.setPassword(basicPasswordEncryptor.encryptPassword(userDTO.getPassword()));
        try {
            userDTO = UserMapper.convertUserMasterToDto(userRepository.save(user));
        } catch (DataIntegrityViolationException ex) {
            if (ex.getMessage().contains("uk_hc_user_email_id")) {
                throw new IllegalArgumentException("User with the provided email ID already exists");
            }
            throw ex;
        } 
        return userDTO;
    }

    @Override
    public UserDTO updateUser(String userName, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isPresent()) {
            User updatedUser = userRepository.save(UserMapper.convertUserDtoToMaster(userDTO, userOptional.get()));
            return UserMapper.convertUserMasterToDto(updatedUser);
        }

        throw new ResourceNotFoundException("User not found for userName: " + userName);

    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.convertUserMasterListToDtoList(users);
    }

    @Override
    public List<User> getUsersByOrgId(Long orgId) {
        return userRepository.findByOrgId(orgId);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(UserMapper::convertUserMasterToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for userId: " + userId));
    }


}
