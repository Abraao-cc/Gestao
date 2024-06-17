package com.timuila.gestao.services;

import com.timuila.gestao.dominio.Review;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface ReviewService {

    Review save(Review review);

    Review update(Review review);

    void delete(UUID id);

    List<Review> getReviews();
}
