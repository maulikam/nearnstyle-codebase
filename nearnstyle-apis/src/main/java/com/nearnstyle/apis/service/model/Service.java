package com.nearnstyle.apis.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(nullable = false, unique = true)
    private String serviceName;

    @Column(nullable = false)
    private double price;  // Cost of the service

    @Column(length = 500)
    private String description; // Description or details about the service

    @Column(nullable = false)
    private int durationInMinutes; // Duration of the service

    @Column(length = 500)
    private String toolsRequired; // Tools or products used for the service

    @Column
    private boolean isActive; // To indicate if the service is currently active or not (e.g., seasonal services)

    // Any other attributes that may be required:
    @Column
    private int maxBookingPerDay; // Maximum bookings allowed for the service in a single day

    @Column(length = 500)
    private String prerequisites; // Any prerequisites or preparation required from the client

}

