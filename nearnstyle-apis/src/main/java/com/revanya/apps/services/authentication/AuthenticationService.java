package com.revanya.apps.services.authentication;

import com.revanya.apps.services.user.entities.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationService {

    @Inject
    OTPService otpService;  // A service that handles OTP generation and validation

    @Inject
    KeycloakService keycloakService;  // A service that communicates with Keycloak

    @POST
    @Path("/request-otp")
    public Response requestOtp(@FormParam("mobileNumber") String mobileNumber) {
        // Generate and send OTP to the mobile number
        String otp = otpService.generateOtp(mobileNumber);
        otpService.sendOtpViaSms(mobileNumber, otp);
        return Response.ok("OTP sent").build();
    }

    @POST
    @Path("/verify-otp")
    public Response verifyOtp(@FormParam("mobileNumber") String mobileNumber, @FormParam("otp") String otp) {
        OtpEntity otpEntity = otpService.validateOtp(mobileNumber, otp);

        if (otpEntity != null) {
            User existingUser = userRepository.findByMobileNumber(mobileNumber);

            if (existingUser != null) {
                // If user exists with this mobile number, generate a Keycloak token for the user
                String token = keycloakService.getTokenForUser(mobileNumber);
                return Response.ok(token).build();
            } else {
                // No user found with this mobile number
                return Response.status(Response.Status.FORBIDDEN).entity("User not registered").build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid OTP").build();
        }
    }

}

