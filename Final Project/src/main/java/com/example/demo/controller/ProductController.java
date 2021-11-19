package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final IProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                                     @RequestParam(required = false) String name) {
        try {
            if (name != null) {
                Pageable pageRequest = PageRequest.of(page, size, Sort.by(name));
                Page<Product> productPage = productService.getAll(pageRequest);
                List<Product> products = productPage.getContent();
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                Pageable pageRequest = PageRequest.of(page, size);
                Page<Product> productPage = productService.getAll(pageRequest);
                List<Product> products = productPage.getContent();
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") long id) {
        try {
            Product product = productService.getById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setBrand(productDTO.getBrand());
            product.setCalories(product.getCalories());
            product.setProteins(productDTO.getProteins());
            product.setFats(productDTO.getFats());
            product.setCarbohydrates(productDTO.getCarbohydrates());
            product.setWeight(productDTO.getWeight());
            Product newProduct = productService.save(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name = "id") long id,
                                                 @RequestBody Product product,
                                                 @PathVariable(name = "dt_update") long dateUpdate) {
        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateUpdate), ZoneId.systemDefault());
            Product updateProduct = productService.update(product, id, date);
            return new ResponseEntity<>(updateProduct, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable(name = "id") long id,
                                                    @PathVariable(name = "dt_update") long dateUpdate) {
        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateUpdate), ZoneId.systemDefault());
            productService.delete(id, date);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
