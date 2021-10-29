package com.example.demo.repository;

import com.example.demo.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static final ProductRepository instance = new ProductRepository();

    private List<Product> productList = new ArrayList<>();

    private ProductRepository() {
    }

    public static ProductRepository getInstance(){
        return instance;
    }

    public List<Product> getAllProducts(){
        return productList;
    }

    public Product getProductById(int id){
        return productList.get(id);
    }

    public Product addProduct(Product product){
        productList.add(product);
        int index = productList.size();
        return productList.get(index-1);
    }

    public Product updateProduct(int id, Product product){
        productList.add(id,product);
        return productList.get(id);
    }

    public void deleteProduct(int id){
        productList.remove(id);
    }



}
