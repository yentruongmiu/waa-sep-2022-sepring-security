package edu.miu.productReview.service;

import edu.miu.productReview.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto product);
    List<ProductDto> findAll();
    ProductDto findById(int id);
    ProductDto update(int id, ProductDto productDto);
    void delete(int id);
    List<ProductDto> findAllByPriceGreaterThan(Float minPrice);
    List<ProductDto> findAllByCategoryAndPriceLessThan(String cat, Float price);
    List<ProductDto> findAllByNameContaining(String keyword);
}
