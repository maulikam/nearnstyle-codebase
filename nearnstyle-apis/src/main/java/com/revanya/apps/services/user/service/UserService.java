package com.revanya.apps.services.user.service;


import com.revanya.apps.services.user.dto.UserDTO;
import com.revanya.apps.services.user.entities.User;
import com.revanya.apps.services.user.mappers.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.toDTO(user);
    }

    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.toDTO(user);
    }

    public List<UserDTO> findByCity(String city) {
        List<User> users = userRepository.findByCity(city);
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public List<UserDTO> findUsersRegisteredAfter(Date date) {
        List<User> users = userRepository.findUsersRegisteredAfter(date);
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public List<UserDTO> findByRoleName(String roleName) {
        List<User> users = userRepository.findByRoleName(roleName);
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public UserDTO findByMobileNumber(String mobileNumber) {
        User user = userRepository.findByMobileNumber(mobileNumber);
        return userMapper.toDTO(user);
    }

    public List<UserDTO> findUsersWithBookings() {
        List<User> users = userRepository.findUsersWithBookings();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.persist(user);
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        if (userRepository.findById(userId) == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
        User user = userMapper.toEntity(userDTO);
        user.setId(userId); // Make sure the ID is set to avoid creating a new user
        userRepository.getEntityManager().merge(user);
        return userMapper.toDTO(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}

