package com.nearnstyle.apis.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    public void setUserDetailsService(SecurityUserService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Autowired
    SecurityUserService securityUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws ResourceNotFoundException {
        try {
            Authentication auth;
            if (authentication.getDetails() instanceof Map) {
                Map<String, String> form = (Map<String, String>) authentication.getDetails();
                String otp = form.get("otp");
                String mobileNumber = form.get("mobilenumber");
                String googleId = form.get("googleId");

                if (otp != null && !otp.isEmpty()
                        && mobileNumber != null && !mobileNumber.isEmpty()
                        && googleId != null && !googleId.isEmpty()) {

                    UserDetails userDetails = securityUserService.loadUserByMobileAndOTPAndGoogleId(mobileNumber, otp, googleId);
                    auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                }else if (otp != null && !otp.isEmpty() && mobileNumber != null && !mobileNumber.isEmpty()) {

                        UserDetails userDetails = securityUserService.loadUserByMobileAndOTP(mobileNumber, otp);
                        auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                } else if (googleId != null && !googleId.isEmpty()) {
                        UserDetails userDetails = securityUserService.loadUserByGoogleId(googleId);
                        auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                }
                else {
                    auth = super.authenticate(authentication);
                }
            } else {
                auth = super.authenticate(authentication);
            }
            return auth;
        } catch (BadCredentialsException e) {
            throw e;
        } catch (LockedException e) {
            throw new LockedException("Your account is locked for 15 minutes. Please try again later");
        }
    }
}
