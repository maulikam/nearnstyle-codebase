package com.nearnstyle.apis.appointment.model;

import com.nearnstyle.apis.salon.model.Salon;
import com.nearnstyle.apis.service.model.Service;
import com.nearnstyle.apis.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceId", nullable = false)
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salonId", nullable = false) // This line establishes the relationship with Salon
    private Salon salon; // Use the Salon entity instead of just the salonId

    @Column(nullable = false)
    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private AppointmentStatus status;

    @Column(length = 200)
    private String specialRequests;

    public enum AppointmentStatus {
        CONFIRMED, COMPLETED, CANCELED
    }
}
