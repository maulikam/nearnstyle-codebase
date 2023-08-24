package com.nearnstyle.apis.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long reviewId;

    private Long userId; // reference to the user by its ID

    private int rating;

    private String comment;

    private LocalDateTime reviewDate;
}

