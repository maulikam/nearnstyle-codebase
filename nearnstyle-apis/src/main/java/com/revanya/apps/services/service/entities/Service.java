package com.revanya.apps.services.service.entities;


import com.revanya.apps.services.booking.entities.Booking;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    // Relating service to its type
    @ManyToOne
    @JoinColumn(name = "service_type_id", nullable = false)
    private ServiceType serviceType;

    private String serviceName;

    private Double serviceCost;

    // Duration of the service in minutes. Helps in allocating resources/slots.
    private Integer duration;

    // Description or details about the service
    @Column(length = 500)
    private String description;

    // If the service requires any special tools or preparations
    private Boolean specialEquipmentRequired;

    // Any potential discount or promotion associated with the service
    private Double discount;

    // Rating given by the user after the service is done (scale 1 to 5 for simplicity)
    private Float rating;

    // Feedback or review text given by the user post service completion
    @Column(length = 1000)
    private String feedback;


}





