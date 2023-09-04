package com.revanya.apps.services.review.mappers;

import com.revanya.apps.services.review.dto.ReviewDTO;
import com.revanya.apps.services.review.entities.Review;
import com.revanya.apps.services.salon.entities.Salon;
import com.revanya.apps.services.user.entities.User;

public class ReviewMapper {

    public static Review toEntity(ReviewDTO dto) {
        if (dto == null) {
            return null;
        }

        Review review = new Review();

        review.setId(dto.getId());

        User user = new User();
        user.setId(dto.getUserId());  // Assuming User has a setId() method.
        review.setUser(user);         // Set user reference with only ID, fetch or merge logic will be in service layer.

        Salon salon = new Salon();
        salon.setId(dto.getSalonId()); // Assuming Salon has a setId() method.
        review.setSalon(salon);        // Set salon reference with only ID.

        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setDate(dto.getDate());

        return review;
    }

    public static ReviewDTO toDTO(Review entity) {
        if (entity == null) {
            return null;
        }

        ReviewDTO dto = new ReviewDTO();

        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setSalonId(entity.getSalon().getId());
        dto.setRating(entity.getRating());
        dto.setComment(entity.getComment());
        dto.setDate(entity.getDate());
        dto.setUserName(entity.getUser().getUsername());  // Assuming User has a getName() method.
        dto.setSalonName(entity.getSalon().getName()); // Assuming Salon has a getName() method.

        return dto;
    }
}
