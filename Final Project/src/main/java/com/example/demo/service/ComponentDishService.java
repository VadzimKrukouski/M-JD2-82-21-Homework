package com.example.demo.service;

import com.example.demo.dao.api.IComponentDishDao;
import com.example.demo.model.ComponentDish;
import com.example.demo.service.api.IAppService;
import com.example.demo.service.api.IComponentDishService;
import org.springframework.stereotype.Service;

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
    public ComponentDish save(ComponentDish model) {
        return componentDishDao.save(model);
    }

    @Override
    public List<ComponentDish> getAll() {
        return componentDishDao.findAll();
    }

    @Override
    public ComponentDish update(ComponentDish model, long id) {
        ComponentDish updateComponentDish = getById(id);
        updateComponentDish.setDish(model.getDish());
        updateComponentDish.setProduct(model.getProduct());
        updateComponentDish.setWeightProduct(model.getWeightProduct());
        return save(updateComponentDish);
    }

    @Override
    public void delete(long id) {
        componentDishDao.deleteById(id);
    }
}
