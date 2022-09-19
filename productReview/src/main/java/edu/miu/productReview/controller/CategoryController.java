package edu.miu.productReview.controller;

import edu.miu.productReview.dto.CategoryDto;
import edu.miu.productReview.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categorySvc;

    @PostMapping
    public CategoryDto save(@RequestBody CategoryDto category) {
        return categorySvc.save(category);
    }

    @GetMapping
    public List<CategoryDto> findAll() {
        return categorySvc.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable int id) {
        return categorySvc.findById(id);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable int id, @RequestBody CategoryDto cat) {
        return categorySvc.update(id, cat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        categorySvc.delete(id);
    }
}
