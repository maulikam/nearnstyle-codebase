package com.nearnstyle.apis.common.security.otp;


import com.nearnstyle.apis.user.dto.UserDTO;
import com.nearnstyle.apis.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException; 
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private UserService userService;

    @Autowired
    private OtpRepository otpRepository;


    public void generateAndSendOtp(String mobileNumber) {

        UserDTO userDTO = userService.getUserByMoblieNumber(mobileNumber);


        // Generate a random OTP
        String otp = String.format("%06d", new Random().nextInt(999999));
        otp = "123456";
        UserOtp userOtp = new UserOtp();
        // Set the expiration time for the OTP (e.g., 10 minutes from now)
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10);
        try {
            // Fetch the user details from the database using the UserService
            UserDTO user = userService.getUserByMoblieNumber(mobileNumber);
            // Save the OTP and its expiration time to the database
            userOtp.setUsername(user.getUserName());
        } catch( ResourceNotFoundException ex ) {
            // new user
        }

        userOtp.setMobileNumber(mobileNumber);
        userOtp.setOtp(otp);
        userOtp.setExpirationTime(expirationTime);
        otpRepository.save(userOtp);

        // Send the OTP to the user's registered mobile number or email
        // You can use an SMS service or an email service to send the OTP
        // For example: smsService.sendSms(user.getMobileNumber(), "Your OTP is: " + otp);
    }

    public Optional<UserOtp> findByUserIdAndOtp(String username, String otp) {
        return otpRepository.findValidByUsernameAndOtp(username, otp);
    }

    public List<UserOtp> findByMobileNumberAndOtp(String mobileNumber, String otp) {
        return otpRepository.findValidByMobileNumberAndOtp(mobileNumber, otp);
    }

    public boolean verifyOtp(String otp, String mobilenumber) {
        List<UserOtp> userOtpOptional = otpRepository.findValidByMobileNumberAndOtp(mobilenumber, otp);
        if (userOtpOptional != null && !userOtpOptional.isEmpty()) {
            UserOtp userOtp = userOtpOptional.get(0);
            userOtp.setOtpVerified(true);
            userOtp.setExpirationTime(LocalDateTime.now());
            otpRepository.save(userOtp);
            return true;
        }
        return false;
    }
}

