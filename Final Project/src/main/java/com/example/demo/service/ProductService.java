package com.example.demo.service;

import com.example.demo.dao.api.IProductDao;
import com.example.demo.model.Product;
import com.example.demo.service.api.IAppService;
import com.example.demo.service.api.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Product> getAll() {
        return productDao.findAll();
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
        return save(updateProduct);
    }

    @Override
    public void delete(long id) {
        productDao.deleteById(id);
    }
}
