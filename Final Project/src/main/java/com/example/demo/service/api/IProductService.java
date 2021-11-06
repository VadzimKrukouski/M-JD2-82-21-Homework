package com.example.demo.service.api;

import com.example.demo.model.Product;

import java.util.List;

public interface IProductService {
    Product getById(long id);
    Product save(Product model);
    List<Product> getAll();
    Product update(Product model, long id);
    void delete(long id);
}
