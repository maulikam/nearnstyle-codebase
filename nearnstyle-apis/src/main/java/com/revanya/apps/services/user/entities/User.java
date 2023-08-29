package com.revanya.apps.services.user.entities;

import com.revanya.apps.services.booking.entities.Booking;
import com.revanya.apps.services.geolocation.entities.GeoLocation;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    // No need to store the password when using Keycloak

    private String fullName;

    @Column(unique = true)
    private String mobileNumber;

    @Embedded
    private Address address;

    @ElementCollection
    private Map<String, String> preferences = new HashMap<>(); // Key-Value pairs for user preferences

    private String profilePictureURL;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @OneToOne
    @JoinColumn(name = "location_id")
    private GeoLocation location;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    // Address embedded entity
    @Embeddable
    @Getter
    @Setter
    public static class Address {
        private String street;
        private String city;
        private String state;
        private String zip;
        private String country;

        // Getters and setters
    }

}
