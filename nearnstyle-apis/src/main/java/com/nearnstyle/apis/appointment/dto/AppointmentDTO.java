package com.nearnstyle.apis.appointment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Long appointmentId;

    private Long userId;  // reference to the user by its ID

    private Long serviceId; // reference to the service by its ID

    private Long salonId;  // reference to the salon by its ID

    private LocalDateTime appointmentDate;

    private AppointmentStatus status;

    private String specialRequests;

    public enum AppointmentStatus {
        CONFIRMED, COMPLETED, CANCELED
    }
}

