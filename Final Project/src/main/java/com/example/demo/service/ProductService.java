package com.example.demo.service;

import com.example.demo.dao.IProductDao;
import com.example.demo.model.Product;
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
    public Product getProductById(long id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.findAll();
    }

    @Override
    public Product updateProduct(Product product, long id) {
        Product updateProduct = getProductById(id);
        updateProduct.setName(product.getName());
        updateProduct.setCalories(product.getCalories());
        updateProduct.setCarbohydrates(product.getCarbohydrates());
        updateProduct.setProteins(product.getProteins());
        updateProduct.setFats(product.getFats());
        return saveProduct(updateProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productDao.deleteById(id);
    }
}
