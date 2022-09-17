package edu.miu.productReview.repo;

import edu.miu.productReview.domain.Category;
import edu.miu.productReview.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
    List<Product> findAllByPriceGreaterThan(Float minPrice);
    List<Product> findAllByCategoryAndPriceLessThan(Category cat, Float maxPrice);
    List<Product> findAllByNameContaining(String keyword);
}
