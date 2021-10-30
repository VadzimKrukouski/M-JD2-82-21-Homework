package com.example.demo.service.api;

import com.example.demo.model.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(long id);
    Product saveProduct(Product product);
    List<Product> getAllProduct();
    Product updateProduct(Product product, long id);
    void deleteProduct(long id);
}
