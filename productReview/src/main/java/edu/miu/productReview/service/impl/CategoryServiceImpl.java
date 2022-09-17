package edu.miu.productReview.service.impl;
import edu.miu.productReview.domain.Address;
import edu.miu.productReview.domain.Category;
import edu.miu.productReview.dto.CategoryDto;
import edu.miu.productReview.repo.CategoryRepo;
import edu.miu.productReview.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper mapper;
    @Override
    public CategoryDto save(CategoryDto category) {
        Category result = categoryRepo.save(mapper.map(category, Category.class));
        return mapper.map(result, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> cats = new ArrayList<>();
        categoryRepo.findAll().forEach(cats::add);
        return cats.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();
    }

    @Override
    public CategoryDto findById(int id) {
        return categoryRepo.findById(id)
                .map(cat -> mapper.map(cat, CategoryDto.class))
                .orElse(new CategoryDto());
    }

    @Override
    public CategoryDto update(int id, CategoryDto category) {
        Category cat = categoryRepo.findById(id).get();
        if(cat != null) {
            category.setId(cat.getId());
            Category result = categoryRepo.save(mapper.map(category, Category.class));
            return mapper.map(result, CategoryDto.class);
        } else throw new IllegalArgumentException();
    }

    @Override
    public void delete(int id) {
        categoryRepo.deleteById(id);
    }
}
