package com.revanya.apps.services.geolocation.entities;

import com.revanya.apps.services.salon.entities.Salon;
import com.revanya.apps.services.user.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "geolocation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    private String description; // A textual description of the location

    // Relationships to other entities

    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Salon salon;

    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "serviceArea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Salon> salonsInServiceArea;

    // We may want a radius for defining service
    private Double radius;

}
