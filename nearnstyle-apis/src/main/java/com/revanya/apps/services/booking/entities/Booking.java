package com.revanya.apps.services.booking.entities;

import com.revanya.apps.services.salon.entities.Salon;
import com.revanya.apps.services.service.entities.Service;
import com.revanya.apps.services.user.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "salon_id", nullable = false)
    private Salon salon;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date bookingDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date serviceDate; // The actual date/time the service is booked for

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Service> services;

    private Double totalCost;

    // The special instructions or requirements given by the user for the booking
    @Column(length = 1000)
    private String specialInstructions;

    // Mode of payment chosen by the user, e.g., CASH, CREDIT_CARD, etc.
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    // If there was any discount applied to the booking
    private Double discount;

    // If any promo code was applied
    private String promoCode;

    // Rating given by the user after the service
    private Double userRating;

    // Feedback or review provided by the user after the service
    @Column(length = 2000)
    private String userFeedback;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", salon=" + salon +
                ", bookingDate=" + bookingDate +
                ", serviceDate=" + serviceDate +
                ", status=" + status +
                ", totalCost=" + totalCost +
                ", specialInstructions='" + specialInstructions + '\'' +
                ", paymentMode=" + paymentMode +
                ", discount=" + discount +
                ", promoCode='" + promoCode + '\'' +
                ", userRating=" + userRating +
                ", userFeedback='" + userFeedback + '\'' +
                '}';
    }

    public enum BookingStatus {
        PENDING, CONFIRMED, CANCELED, COMPLETED
    }

    public enum PaymentMode {
        CASH, CREDIT_CARD, DEBIT_CARD, WALLET, UPI
    }
}
