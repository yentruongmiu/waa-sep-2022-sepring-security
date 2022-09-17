package edu.miu.productReview.repo;

import edu.miu.productReview.domain.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends CrudRepository<Review, Integer> {
    List<Review> findAllByProduct(Integer productId);
}
