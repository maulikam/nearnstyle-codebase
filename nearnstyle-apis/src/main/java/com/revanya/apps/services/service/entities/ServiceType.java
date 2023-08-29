package com.revanya.apps.services.service.entities;

import com.revanya.apps.services.salon.entities.Salon;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "service_types")
public class ServiceType  extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    // Average duration of the services of this type (in minutes)
    private Integer avgDuration;

    // Indicator if the service type generally requires special equipment or preparations
    private Boolean specialEquipmentRequired;

    // The icon or image URL representing this service type
    @Column(length = 1000)
    private String iconUrl;

    // Services related to this type
    @OneToMany(mappedBy = "serviceType", cascade = CascadeType.ALL)
    private Set<Service> services;

    @ManyToOne
    @JoinColumn(name = "salon_id", nullable = false)
    private Salon salon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceType that = (ServiceType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", avgDuration=" + avgDuration +
                ", specialEquipmentRequired=" + specialEquipmentRequired +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }

    // ... other utility methods if required ...
}
