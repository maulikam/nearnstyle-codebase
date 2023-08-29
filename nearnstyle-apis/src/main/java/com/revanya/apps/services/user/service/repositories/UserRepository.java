package com.revanya.apps.services.user.service.repositories;

import com.revanya.apps.services.user.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    // 1. Find user by username
    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

    // 2. Find user by email
    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }

    // 3. Find users by city (using embedded address)
    public List<User> findByCity(String city) {
        return list("address.city", city);
    }

    // 4. Find users registered after a certain date
    public List<User> findUsersRegisteredAfter(Date date) {
        return list("registrationDate > ?1", date);
    }

    // 5. Find users with a specific role name
    public List<User> findByRoleName(String roleName) {
        // This method utilizes a JOIN to get users with a specific role.
        return list("SELECT u FROM User u JOIN u.roles r WHERE r.name = ?1", roleName);
    }

    // 6. Find users by mobile number
    public User findByMobileNumber(String mobileNumber) {
        return find("mobileNumber", mobileNumber).firstResult();
    }

    // 7. Fetch users who have made bookings (or similar relations)
    public List<User> findUsersWithBookings() {
        return list("SELECT u FROM User u WHERE u.bookings IS NOT EMPTY");
    }


}

