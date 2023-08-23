package com.nearnstyle.apis.user.controller;

import com.nearnstyle.apis.user.dto.UserDTO;
import com.nearnstyle.apis.user.mapper.UserMapper;
import com.nearnstyle.apis.user.model.User;
import com.nearnstyle.apis.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDto) {
        try {
            UserDTO createdUser = userService.registerUser(userDto);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalStateException expected) {
            return ResponseEntity.badRequest().body(expected.getMessage());
        }
    }


    @PostMapping("/{userName}")
    public ResponseEntity<Object> createUser(@PathVariable(value = "userName") String userName, @RequestBody UserDTO userDto) {
        try {
            UserDTO createdUser = userService.createUser(userName, userDto);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalStateException expected) {
            return ResponseEntity.badRequest().body("User already exists!");
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "userName") String userName, @RequestBody UserDTO userDto) {
        UserDTO updatedUser = userService.updateUser(userName, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/mobile")
    public ResponseEntity<UserDTO> getUserByMobileNumber(@RequestParam String mobileNumber) {
        UserDTO user = userService.getUserByMoblieNumber(mobileNumber);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/google")
    public ResponseEntity<UserDTO> getUserByGoogleId(@RequestParam String googleId) {
        UserDTO user = userService.getUserByGoogleId(googleId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/organisation/{organisationId}")
    public ResponseEntity<Object> getUsersByOrganisationId (@PathVariable Long organisationId) {
        try{
            List<User> users = userService.getUsersByOrgId(organisationId);

            return ResponseEntity.ok(UserMapper.convertUserMasterListToDtoList(users));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error fetching user details by org id: " + organisationId + " : " + e.getMessage());
        }
    }
}
