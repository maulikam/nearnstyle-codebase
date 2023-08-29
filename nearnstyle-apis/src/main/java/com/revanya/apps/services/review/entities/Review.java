package com.revanya.apps.services.review.entities;

import com.revanya.apps.services.salon.entities.Salon;
import com.revanya.apps.services.user.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to the user who wrote the review
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Rating out of 5
    @Column(nullable = false)
    private Float rating;

    // Review text
    @Column(length = 1000) // You can adjust this length as per your requirement
    private String comment;

    // Date of the review
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    // Reference to the salon being reviewed
    @ManyToOne
    @JoinColumn(name = "salon_id", nullable = false)
    private Salon salon;

    // Optionally, you can also link this to a specific service or salon if needed:
    // @ManyToOne
    // @JoinColumn(name = "service_id", nullable = false)
    // private Service service;
}
