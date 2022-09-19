package edu.miu.productReview.controller;

import edu.miu.productReview.dto.ProductDto;
import edu.miu.productReview.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable int id, @RequestBody ProductDto product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @GetMapping("/filter-by-minprice")
    public List<ProductDto> findAllByPriceGreaterThan(@RequestParam Float price) {
        return productService.findAllByPriceGreaterThan(price);
    }

    @GetMapping("/filter-by-cat-maxprice")
    public List<ProductDto> findAllByCategoryAndPriceLessThan(
            @RequestParam (required=true) String name,
            @RequestParam Float price) {
        return productService.findAllByCategoryAndPriceLessThan(name, price);
    }

    @GetMapping("/filter-by-productname")
    public List<ProductDto> findAllByNameContaining(@RequestParam String name) {
        return productService.findAllByNameContaining(name);
    }
}
