package com.revanya.apps.services.authentication;

import jakarta.enterprise.context.ApplicationScoped;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.security.SecureRandom;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
 

@ApplicationScoped
public class OTPService {

    private final SecureRandom random = new SecureRandom();

    @Inject
    OtpRepository otpRepository; // Inject the repository for database operations

    @Transactional
    public String generateOtp(String mobileNumber) {
        String otp = String.format("%06d", random.nextInt(999999));

        // Store OTP in the database
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setMobileNumber(mobileNumber);
        otpEntity.setOtp("123456");
        otpRepository.persist(otpEntity);

        return otp;
    }

    @Transactional
    public OtpEntity validateOtp(String mobileNumber, String otp) {
        OtpEntity otpEntity = otpRepository.findByMobileNumber(mobileNumber);
        if (otpEntity != null && otpEntity.getOtp().equals(otp)) {
            return otpEntity;
        } else {
            return null;
        }
    }


    @Transactional
    public void clearOtp(String mobileNumber) {
        OtpEntity otpEntity = otpRepository.findByMobileNumber(mobileNumber);
        if (otpEntity != null) {
            otpRepository.delete(otpEntity);
        }
    }

    /**
     * Sends the OTP via SMS (placeholder implementation).
     * Replace this method with your actual SMS gateway integration code.
     */
    public void sendOtpViaSms(String mobileNumber, String otp) {
        // Replace the following lines with your SMS gateway integration code
        String apiKey = "YOUR_SMS_GATEWAY_API_KEY";
        String smsGatewayUrl = "https://api.example.com/send-sms";

        String message = "Your OTP is: " + otp;

        // Replace this with your actual HTTP client code to send the SMS
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(smsGatewayUrl))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"to\":\"" + mobileNumber + "\",\"message\":\"" + message + "\"}"))
                .build();

//        try {
//            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//            if (response.statusCode() == 200) {
//                System.out.println("OTP sent successfully via SMS");
//            } else {
//                System.out.println("Failed to send OTP via SMS");
//            }
//        } catch (IOException | InterruptedException e) {
//            System.out.println("Error sending OTP via SMS: " + e.getMessage());
//        }
    }
}
