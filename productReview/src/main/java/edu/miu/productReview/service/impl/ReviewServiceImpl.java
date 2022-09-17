package edu.miu.productReview.service.impl;

import edu.miu.productReview.domain.Review;
import edu.miu.productReview.dto.ReviewDto;
import edu.miu.productReview.repo.ReviewRepo;
import edu.miu.productReview.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ReviewDto save(ReviewDto review) {
        Review result = reviewRepo.save(mapper.map(review, Review.class));
        return mapper.map(result, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> findAll() {
        List<Review> reviews = new ArrayList<>();
        reviewRepo.findAll().forEach(reviews::add);
        return reviews.stream().map(review -> mapper.map(review, ReviewDto.class)).toList();
    }

    @Override
    public ReviewDto findById(int id) {
        return reviewRepo.findById(id).map(review -> mapper.map(review, ReviewDto.class)).orElse(new ReviewDto());
    }

    @Override
    public ReviewDto update(int id, ReviewDto review) {
        Review result = reviewRepo.findById(id).get();
        if(result != null) {
            review.setId(result.getId());
            reviewRepo.save(mapper.map(review, Review.class));
            return review;
        }
        return null;
    }

    @Override
    public void delete(int id) {
        reviewRepo.deleteById(id);
    }

    @Override
    public List<ReviewDto> findAllByProduct(int productId) {
        List<Review> reviews = new ArrayList<>();
        reviewRepo.findAllByProduct(productId).forEach(reviews::add);
        return reviews.stream().map(review -> mapper.map(review, ReviewDto.class)).toList();
    }
}
