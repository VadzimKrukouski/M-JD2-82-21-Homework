package com.example.demo.service.api;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product getById(long id);
    Product save(Product model);
    Page<Product> getAll(Pageable pageable);
    Product update(Product model, long id);
    void delete(long id);
}
