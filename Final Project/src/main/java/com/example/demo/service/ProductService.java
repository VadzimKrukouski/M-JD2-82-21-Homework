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
    public Product save(Product product) {
        LocalDateTime localDateTime = LocalDateTime.now();
        product.setDateCreate(localDateTime);
        product.setDateUpdate(localDateTime);
        return productDao.save(product);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public Product update(Product product, long id) {
        Product updateProduct = getById(id);

        updateProduct.setName(product.getName());
        updateProduct.setCalories(product.getCalories());
        updateProduct.setCarbohydrates(product.getCarbohydrates());
        updateProduct.setProteins(product.getProteins());
        updateProduct.setFats(product.getFats());
        updateProduct.setBrand(product.getBrand());
        updateProduct.setWeight(product.getWeight());
        updateProduct.setUser(product.getUser());
        updateProduct.setDateUpdate(LocalDateTime.now());

        return save(updateProduct);
    }

    @Override
    public void delete(long id) {
        productDao.deleteById(id);
    }
}
