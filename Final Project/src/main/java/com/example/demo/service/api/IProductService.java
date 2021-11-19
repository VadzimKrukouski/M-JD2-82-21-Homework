package com.example.demo.service.api;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IProductService {
    Product getById(long id);
    Product save(Product product);
    Page<Product> getAll(Pageable pageable);
    Product update(Product product, long id, LocalDateTime dateUpdate);
    void delete(long id, LocalDateTime dateTime);
}
