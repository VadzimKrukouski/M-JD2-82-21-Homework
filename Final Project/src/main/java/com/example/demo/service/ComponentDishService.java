package com.example.demo.service;

import com.example.demo.dao.api.IComponentDishDao;
import com.example.demo.model.ComponentDish;
import com.example.demo.service.api.IAppService;
import com.example.demo.service.api.IComponentDishService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComponentDishService implements IComponentDishService {
    private final IComponentDishDao componentDishDao;

    public ComponentDishService(IComponentDishDao componentDishDao) {
        this.componentDishDao = componentDishDao;
    }

    @Override
    public ComponentDish getById(long id) {
        return componentDishDao.findById(id).orElse(null);
    }

    @Override
    public ComponentDish save(ComponentDish componentDish) {
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
