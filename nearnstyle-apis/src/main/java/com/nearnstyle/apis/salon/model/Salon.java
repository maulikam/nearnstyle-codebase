package com.nearnstyle.apis.salon.model;

import com.nearnstyle.apis.appointment.model.Appointment;
import com.nearnstyle.apis.service.model.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "salons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salonId;

    @Column(nullable = false, unique = true)
    private String salonName;

    @Column(nullable = false, length = 500)
    private String address; // Address of the salon

    @Column
    private String phoneNumber;

    @Column(length = 500)
    private String description; // A brief description about the salon

    @Column
    private boolean isActive; // To indicate if the salon is currently operational or not

    @OneToMany(mappedBy = "salon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "salon_services",
            joinColumns = @JoinColumn(name = "salon_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> servicesOffered;

    @Column(nullable = false)
    private String operatingHours; // Example: "Mon-Fri: 9am-8pm, Sat: 10am-6pm"

    @Column(length = 1000)
    private String facilities; // Example: "WiFi, Waiting Area, Complimentary Beverages"

    @Column
    private String ownerName; // Name of the salon owner

    @OneToMany(mappedBy = "salon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OperatingHours> operatingHoursList;
}
