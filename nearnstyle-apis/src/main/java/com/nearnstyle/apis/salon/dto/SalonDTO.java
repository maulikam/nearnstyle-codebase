package com.nearnstyle.apis.salon.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SalonDTO {
    private Long id;
    private String name;
    private String code;
    private String address;
    private String ad_state;
    private String ad_district;
    private String ad_city;
    private Integer ad_pincode;
    private String state;
    private Double longitude;
    private Double latitude;
    private LocalDateTime codeGeneratedAt;
    private LocalDateTime codeExpiresAt;
    private String contactName;
    private String contactEmail;
    private String contactMobile;


}

