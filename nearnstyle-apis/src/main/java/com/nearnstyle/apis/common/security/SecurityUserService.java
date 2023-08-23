package com.nearnstyle.apis.common.security;

import com.nearnstyle.apis.common.security.otp.OtpService;
import com.nearnstyle.apis.role.dto.RoleDTO;
import com.nearnstyle.apis.role.service.RoleService;
import com.nearnstyle.apis.user.dto.UserDTO;
import com.nearnstyle.apis.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public  class SecurityUserService implements UserDetailsService {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    OtpService otpService;

    public UserDetails loadUserByGoogleId(String googleId) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        String clientId = request.getParameter("client_id");
        String grantType = request.getParameter("grant_type");
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (grantType.equals("password")) {
            Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName(clientId, googleId);
            for (OAuth2AccessToken token : tokens) {
                tokenStore.removeRefreshToken(token.getRefreshToken());
                tokenStore.removeAccessToken(token);
            }
        }

        try {
            UserDTO userDto = userService.getUserByGoogleId(googleId);
            RoleDTO roleDTO = roleService.getRoleById(userDto.getRoleId());


            return new AuthenticationUser(userDto.getId(), userDto.getName(), userDto.getMobileNumber(), googleId,
                    Long.parseLong(userDto.getId() +""), roleDTO.getName(), userDto.getMobileNumber(), userDto.getGender(),
                    userDto.getAddress(), new Date(), true, true, true, false,
                    authorities);

        } catch (ResourceNotFoundException e) {
            throw  new AuthenticationException(HttpStatus.NOT_FOUND.toString(), "User not found", e);
        }

    }

    public UserDetails loadUserByMobileAndOTPAndGoogleId(String mobileNumber, String otp, String googleId)  {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        try {
            UserDTO userDTO = userService.getUserByGoogleId(googleId);
            if (userDTO == null) {
                throw new AuthenticationException("user_not_found", "Invalid Google ID");
            }
        } catch(ResourceNotFoundException ex){
            throw new AuthenticationException("user_not_found", "Invalid Google ID");
        }

        boolean isVerified = otpService.verifyOtp(otp, mobileNumber);
        if (!isVerified) {
            throw new AuthenticationException("otp_invalid", "Invalid OTP");
        }


        String clientId = request.getParameter("client_id");
        String grantType = request.getParameter("grant_type");
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (grantType.equals("password")) {
            Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName(clientId, mobileNumber);
            for (OAuth2AccessToken token : tokens) {
                tokenStore.removeRefreshToken(token.getRefreshToken());
                tokenStore.removeAccessToken(token);
            }
        }

        try {
            UserDTO userDto = userService.getUserByMoblieNumber(mobileNumber);
            RoleDTO roleDTO = roleService.getRoleById(userDto.getRoleId());


            return new AuthenticationUser(userDto.getId(), userDto.getName(), userDto.getMobileNumber(), otp,
                    Long.parseLong(userDto.getId() +""), roleDTO.getName(), userDto.getMobileNumber(), userDto.getGender(),
                    userDto.getAddress(), new Date(), true, true, true, false,
                    authorities);

        } catch (ResourceNotFoundException e) {
            throw  new AuthenticationException(HttpStatus.NOT_FOUND.toString(), "User not found", e);        }

    }

    public UserDetails loadUserByMobileAndOTP(String mobileNumber, String otp)  {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();


        boolean isVerified = otpService.verifyOtp(otp, mobileNumber);
        if (!isVerified) {
            throw new AuthenticationException("otp_invalid", "Invalid OTP");
        }

        String clientId = request.getParameter("client_id");
        String grantType = request.getParameter("grant_type");
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (grantType.equals("password")) {
            Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName(clientId, mobileNumber);
            for (OAuth2AccessToken token : tokens) {
                tokenStore.removeRefreshToken(token.getRefreshToken());
                tokenStore.removeAccessToken(token);
            }
        }

        try {
            UserDTO userDto = userService.getUserByMoblieNumber(mobileNumber);
            RoleDTO roleDTO = roleService.getRoleById(userDto.getRoleId());


            return new AuthenticationUser(userDto.getId(), userDto.getName(), userDto.getMobileNumber(), otp,
                    Long.parseLong(userDto.getId() +""), roleDTO.getName(), userDto.getMobileNumber(), userDto.getGender(),
                    userDto.getAddress(), new Date(), true, true, true, false,
                    authorities);

        } catch (ResourceNotFoundException e) {
            throw  new AuthenticationException(HttpStatus.NOT_FOUND.toString(), "User not found", e);        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        String grantType = request.getParameter("grant_type");
        String clientId = request.getParameter("client_id");
        UserDTO userDto = userService.getUserByUsername(username);

        if (userDto == null) {
            throw new AuthenticationException("user_not_found", "Invalid username");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (grantType.equals("password")) {
            Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName(clientId, username);
            for (OAuth2AccessToken token : tokens) {
                tokenStore.removeRefreshToken(token.getRefreshToken());
                tokenStore.removeAccessToken(token);
            }
        }

        if (userDto.getNoOfAttempts() != null && userDto.getNoOfAttempts() > 2) {
            return new AuthenticationUser(userDto.getId(), userDto.getName(), userDto.getUserName(), userDto.getPassword(),
                    Long.parseLong(userDto.getId() +""), userDto.getRoleName(), userDto.getMobileNumber(), userDto.getGender(),
                    userDto.getAddress(), new Date(), true, true, true, false,
                    authorities);
        } else {
            return new AuthenticationUser(userDto.getId(), userDto.getName(), userDto.getUserName(), userDto.getPassword(),
                    Long.parseLong(userDto.getId() +""), userDto.getRoleName(), userDto.getMobileNumber(), userDto.getGender(),
                    userDto.getAddress(), new Date(), true, true, true, true,
                    authorities);
        }

    }
}
