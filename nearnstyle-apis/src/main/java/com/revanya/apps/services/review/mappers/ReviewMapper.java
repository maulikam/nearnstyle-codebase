package com.revanya.apps.services.review.mappers;

import com.revanya.apps.services.review.dto.ReviewDTO;
import com.revanya.apps.services.review.entities.Review;
import jakarta.inject.Singleton;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta")
@Singleton
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "salon.id", target = "salonId")
    // Optional: if you want to include user name and salon name in the DTO
    @Mapping(source = "user.username", target = "userName")
    @Mapping(source = "salon.name", target = "salonName")
    ReviewDTO toDTO(Review review);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "salonId", target = "salon.id")
    @Mapping(target = "user.username", ignore = true) // We ignore these fields when mapping back to entity
    @Mapping(target = "salon.name", ignore = true)
    Review toEntity(ReviewDTO dto);
}

