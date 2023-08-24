package com.nearnstyle.apis.user.model;

import com.nearnstyle.apis.appointment.model.Appointment;
import com.nearnstyle.apis.review.model.Review;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String emailAddress;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password; // This will store the hashed password

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserType userType;

    private String profilePicture;

    // Assuming a separate Review and Appointment table
    // One user can have many reviews and appointments
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> userRatingsReviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> historyOfAppointments;


    public enum UserType {
        CUSTOMER, SALON_OWNER, ADMIN
    }
}
