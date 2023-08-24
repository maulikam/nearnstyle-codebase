package com.nearnstyle.apis.review.mapper;

import com.nearnstyle.apis.review.dto.ReviewDTO;
import com.nearnstyle.apis.review.model.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDTO toDTO(Review review);

    Review fromDTO(ReviewDTO reviewDTO);

    List<ReviewDTO> toDTOs(List<Review> reviews);

    List<Review> fromDTOs(List<ReviewDTO> reviewDTOs);
}

