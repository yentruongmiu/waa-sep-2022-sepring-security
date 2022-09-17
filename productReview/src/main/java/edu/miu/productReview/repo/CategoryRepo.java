package edu.miu.productReview.repo;

import edu.miu.productReview.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> {
    Category findByName(String name);
}
