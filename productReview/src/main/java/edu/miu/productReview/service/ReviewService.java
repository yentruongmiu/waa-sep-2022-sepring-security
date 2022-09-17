package edu.miu.productReview.service;

import edu.miu.productReview.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto save(ReviewDto review);
    List<ReviewDto> findAll();
    ReviewDto findById(int id);
    ReviewDto update(int id, ReviewDto review);
    void delete(int id);
    List<ReviewDto> findAllByProduct(int productId);
}
