package com.example.demo.service;

import com.example.demo.dao.api.IProductDao;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.security.UserHolder;
import com.example.demo.service.api.IProductService;
import com.example.demo.service.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ProductService implements IProductService {
    private final IProductDao productDao;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ProductService(IProductDao productDao, UserHolder userHolder, IUserService userService) {
        this.productDao = productDao;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    public Product getById(long id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        String loginUser = userHolder.getAuthentication().getName();
        User user = userService.findUserByLogin(loginUser);
        product.setUser(user);
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
        product.setDateCreate(localDateTime);
        product.setDateUpdate(localDateTime);
        return productDao.save(product);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public Product update(Product product, long id, LocalDateTime dateUpdate) {
        try {
            Product updateProduct = getById(id);

            if (updateProduct == null) {
                throw new IllegalArgumentException("Product is not found by ID");
            }

            updateProduct.setName(product.getName());
            updateProduct.setCalories(product.getCalories());
            updateProduct.setCarbohydrates(product.getCarbohydrates());
            updateProduct.setProteins(product.getProteins());
            updateProduct.setFats(product.getFats());
            updateProduct.setBrand(product.getBrand());
            updateProduct.setWeight(product.getWeight());

            String loginUser = userHolder.getAuthentication().getName();
            User user = userService.findUserByLogin(loginUser);
            updateProduct.setUser(user);

            if (updateProduct.getDateUpdate().isEqual(dateUpdate)) {
                return productDao.save(updateProduct);
            } else {
                throw new IllegalArgumentException("Optimistic lock. Product already updated");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error update Product");
        }
    }

    @Override
    public void delete(long id, LocalDateTime dateTime) {
        Product product = getById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product is not found by ID");
        }
        if (Objects.equals(product.getDateUpdate(), dateTime)) {
            productDao.deleteById(id);
        } else {
            throw new IllegalArgumentException("Optimistic lock. Product already updated");
        }
    }
}
