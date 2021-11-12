package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final IProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam (value = "page", defaultValue = "0") int page,
                                                     @RequestParam (value = "size", defaultValue = "10") int size,
                                                     @RequestParam (required = false) String name ){
        if (name!=null){
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
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable (name = "id") long id){
        Product product = productService.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product newProduct = productService.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<Product> updateProduct(@PathVariable (name = "id") long id,
                                                 @RequestBody Product product,
                                                 @PathVariable (name = "dt_update")LocalDateTime dateUpdate){
        Product updateProduct = productService.update(product, id);
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable (name = "id") long id,
                                                    @PathVariable (name = "dt_update") LocalDateTime dateUpdate){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
