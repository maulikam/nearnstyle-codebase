package com.revanya.apps.services.review.service.repositories;


import com.revanya.apps.services.review.entities.Review;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ReviewRepository implements PanacheRepositoryBase<Review, Long> {

    @Inject
    EntityManager em;

    public Review findById(Long id) {
        return find("id", id).firstResult();
    }

    public List<Review> findAllReviews() {
        return listAll();
    }

    public List<Review> findByUserId(Long userId) {
        return list("user.id", userId);
    }

    public List<Review> findBySalonId(Long salonId) {
        return list("salon.id", salonId);
    }

    public void persistReview(Review review) {
        persist(review);
    }

    @Transactional
    public void updateReview(Review review) {
        if (review == null || review.getId() == null) {
            throw new IllegalArgumentException("Invalid review provided for update");
        }

        if (em.find(Review.class, review.getId()) == null) {
            throw new IllegalStateException("Review does not exist in database");
        }

        em.merge(review);
    }


    public void deleteReview(Review review) {
        delete(review);
    }

    // You can add more methods as per your requirements
}

