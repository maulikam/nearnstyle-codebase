package com.nearnstyle.apis.common.security.otp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<UserOtp, Long> {

    UserOtp findByUsername(String username);

    @Query("SELECT u FROM UserOtp u WHERE u.username = :username AND u.otp = :otp AND u.expirationTime > CURRENT_TIMESTAMP ORDER BY u.expirationTime DESC")
    Optional<UserOtp> findValidByUsernameAndOtp(@Param("username") String username, @Param("otp") String otp);

    @Query("SELECT u FROM UserOtp u WHERE u.mobileNumber = :mobileNumber AND u.otp = :otp AND u.expirationTime > CURRENT_TIMESTAMP ORDER BY u.expirationTime DESC")
    List<UserOtp> findValidByMobileNumberAndOtp(@Param("mobileNumber") String mobileNumber, @Param("otp") String otp);


    @Query("SELECT u FROM UserOtp u WHERE u.mobileNumber = :mobileNumber AND u.otp = :otp AND u.expirationTime > CURRENT_TIMESTAMP ORDER BY u.expirationTime DESC")
    Optional<UserOtp> findFirstValidByMobileNumberAndOtp(@Param("mobileNumber") String mobileNumber, @Param("otp") String otp);




}

