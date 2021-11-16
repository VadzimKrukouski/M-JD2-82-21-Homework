package com.example.demo.service;

import com.example.demo.dao.api.IComponentDishDao;
import com.example.demo.model.ComponentDish;
import com.example.demo.model.Product;
import com.example.demo.service.api.IComponentDishService;
import com.example.demo.service.api.IProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComponentDishService implements IComponentDishService {
    private final IComponentDishDao componentDishDao;
    private final IProductService productService;

    public ComponentDishService(IComponentDishDao componentDishDao, IProductService productService) {
        this.componentDishDao = componentDishDao;
        this.productService = productService;
    }

    @Override
    public ComponentDish getById(long id) {
        return componentDishDao.findById(id).orElse(null);
    }

    @Override
    public ComponentDish save(ComponentDish componentDish) {
        Product product = productService.getById(componentDish.getProduct().getId());
        componentDish.setProduct(product);
        LocalDateTime localDateTime = LocalDateTime.now();
        componentDish.setDateCreate(localDateTime);
        componentDish.setDateUpdate(localDateTime);
        return componentDishDao.save(componentDish);
    }

    @Override
    public List<ComponentDish> getAll() {
        return componentDishDao.findAll();
    }

    @Override
    public ComponentDish update(ComponentDish componentDish, long id) {
        ComponentDish updateComponentDish = getById(id);
        updateComponentDish.setProduct(componentDish.getProduct());
        updateComponentDish.setWeightProduct(componentDish.getWeightProduct());
        return componentDishDao.save(updateComponentDish);
    }

    @Override
    public void delete(long id) {
        componentDishDao.deleteById(id);
    }
}
