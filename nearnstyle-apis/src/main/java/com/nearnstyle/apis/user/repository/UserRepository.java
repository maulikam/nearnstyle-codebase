package com.nearnstyle.apis.user.repository;

import com.nearnstyle.apis.user.model.User; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
 
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by username;
     * @param userName user's Username
     * @return user
     */
    Optional<User> findByUserName(String userName);

    Optional<User> findByGoogleId(String googleId);

    Optional<User> findByMobileNumber(String mobileNumber);

    List<User> findByOrgId(Long orgId);

    Optional<User> findByEmailId(String emailId);
}
