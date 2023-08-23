package com.nearnstyle.apis.common.security.otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "hc_user_otps")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserOtp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "otp", nullable = false)
    private String otp;

    @Column(name = "otp_verified", nullable = false)
    private Boolean otpVerified = false;

    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;


}

