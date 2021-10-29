package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam (defaultValue = "0") int page,
                                                     @RequestParam (defaultValue = "10") int size,
                                                     @RequestParam String name){
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable (name = "id") int id){
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<Product> updateProduct(@PathVariable (name = "id") int id,
                                                 @PathVariable (name = "dt_update") Product product){
        Product updateProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updateProduct,HttpStatus.OK);
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable (name = "id") int id,
                                                    @PathVariable (name = "dt_update") Product product){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
