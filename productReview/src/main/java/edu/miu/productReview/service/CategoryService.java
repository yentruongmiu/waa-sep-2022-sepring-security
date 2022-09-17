package edu.miu.productReview.service;

import edu.miu.productReview.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto category);
    List<CategoryDto> findAll();
    CategoryDto findById(int id);
    CategoryDto update(int id, CategoryDto category);
    void delete(int id);

    interface UaaService {
    }
}
