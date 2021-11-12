package com.example.demo.service.api;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Product getById(long id);
    Product save(Product product);
    Page<Product> getAll(Pageable pageable);
    Product update(Product product, long id);
    void delete(long id);
}
