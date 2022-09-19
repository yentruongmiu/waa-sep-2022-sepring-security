package edu.miu.productReview.service.impl;

import edu.miu.productReview.aspect.ExecutionTime;
import edu.miu.productReview.domain.Category;
import edu.miu.productReview.domain.Product;
import edu.miu.productReview.domain.User;
import edu.miu.productReview.dto.ProductDto;
import edu.miu.productReview.repo.CategoryRepo;
import edu.miu.productReview.repo.ProductRepo;
import edu.miu.productReview.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    @ExecutionTime
    public ProductDto save(ProductDto product) {
        Product productEntity = mapper.map(product, Product.class);
        int userId = ((User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId();
        productEntity.setUserId(userId);
        Product result = productRepo.save(productEntity);
        return mapper.map(result, ProductDto.class);
    }

    @Override
    @ExecutionTime
    public List<ProductDto> findAll() {
        List<Product> products = new ArrayList<>();
        productRepo.findAll().forEach(products::add);
        return products.stream().map(prod -> mapper.map(prod, ProductDto.class)).toList();
    }

    @Override
    @ExecutionTime
    public ProductDto findById(int id) {
        return productRepo.findById(id).map(prod -> mapper.map(prod, ProductDto.class)).orElse(new ProductDto());
    }

    @Override
    @ExecutionTime
    public ProductDto update(int id, ProductDto productDto) {
        Product product = productRepo.findById(id).get();
        if(product != null) {
            productDto.setId(product.getId());
            productRepo.save(mapper.map(productDto, Product.class));
            return productDto;
        } else throw new IllegalArgumentException();
    }

    @Override
    @ExecutionTime
    public void delete(int id) {
        if(productRepo.existsById(id)) {
            productRepo.deleteById(id);
        } else throw new IllegalArgumentException();
    }

    @Override
    @ExecutionTime
    public List<ProductDto> findAllByPriceGreaterThan(Float minPrice) {
        List<Product> products = productRepo.findAllByPriceGreaterThan(minPrice);
        return products.stream().map(prod -> mapper.map(prod, ProductDto.class)).toList();
    }

    @Override
    @ExecutionTime
    public List<ProductDto> findAllByCategoryAndPriceLessThan(String cat, Float price) {
        Category category = categoryRepo.findByName(cat);
        List<Product> products = productRepo.findAllByCategoryAndPriceLessThan(category, price);
        return products.stream().map(prod -> mapper.map(prod, ProductDto.class)).toList();
    }

    @Override
    @ExecutionTime
    public List<ProductDto> findAllByNameContaining(String keyword) {
        List<Product> products = productRepo.findAllByNameContaining(keyword);
        return products.stream().map(prod -> mapper.map(prod, ProductDto.class)).toList();
    }
}
