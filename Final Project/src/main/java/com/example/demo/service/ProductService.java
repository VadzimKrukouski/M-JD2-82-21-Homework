package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = ProductRepository.getInstance();
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }

    public Product getProductById(int id){
        return productRepository.getProductById(id);
    }

    public Product addProduct(Product product){
        return productRepository.addProduct(product);
    }

    public Product updateProduct(int id, Product product){
        Product productById = productRepository.getProductById(id);
        productById.setName(product.getName());
        productById.setCalories(product.getCalories());
        productById.setProteins(product.getProteins());
        productById.setFats(product.getFats());
        productById.setCarbohydrates(product.getCarbohydrates());
        return productRepository.updateProduct(id,productById);
    }

    public void deleteProduct(int id){
        productRepository.deleteProduct(id);
    }
}
