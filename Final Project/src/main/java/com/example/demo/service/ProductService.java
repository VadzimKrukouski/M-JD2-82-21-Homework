package com.example.demo.service;

import com.example.demo.dao.api.IProductDao;
import com.example.demo.model.Product;
import com.example.demo.service.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService implements IProductService {
    private final IProductDao productDao;

    public ProductService(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product getById(long id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public Product save(Product model) {
        return productDao.save(model);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public Product update(Product model, long id) {
        Product updateProduct = getById(id);

        updateProduct.setName(model.getName());
        updateProduct.setCalories(model.getCalories());
        updateProduct.setCarbohydrates(model.getCarbohydrates());
        updateProduct.setProteins(model.getProteins());
        updateProduct.setFats(model.getFats());
        updateProduct.setBrand(model.getBrand());
        updateProduct.setWeight(model.getWeight());
        updateProduct.setUser(model.getUser());
        updateProduct.setDateUpdate(LocalDateTime.now());

        return save(updateProduct);
    }

    @Override
    public void delete(long id) {
        productDao.deleteById(id);
    }
}
