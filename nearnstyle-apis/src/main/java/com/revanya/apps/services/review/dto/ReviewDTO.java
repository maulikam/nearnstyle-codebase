package com.revanya.apps.services.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long id;

    // Instead of referencing the whole User entity,
    // you'd typically just use an ID or a small subset of User data in a DTO.
    private Long userId;

    // Rating out of 5
    private Float rating;

    // Review text
    private String comment;

    // Date of the review
    private Date date;

    // Reference to the salon being reviewed
    private Long salonId;

    // If you decide to link the review to a specific service later, you can add:
    // private Long serviceId;

    // Optionally, for the purpose of displaying in a UI,
    // you might also include minimal user and salon info like names:
    private String userName;
    private String salonName;
}

