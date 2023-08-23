package com.nearnstyle.apis.common.security.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/api/otp/generate")
    public ResponseEntity<?> generateOtp(@RequestParam("mobilenumber") String mobilenumber) {
        otpService.generateAndSendOtp(mobilenumber);
        return ResponseEntity.ok("OTP generated and sent to the user's registered mobile number or email.");
    }

    @PostMapping("/api/otp/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam("otp") String otp, @RequestParam("mobilenumber") String mobilenumber) {
        boolean verified = otpService.verifyOtp(otp, mobilenumber);
        if (verified) {
            return ResponseEntity.ok("OTP verified.");
        } else {
            return ResponseEntity.ok("Invalid OTP.");
        }
    }
}

