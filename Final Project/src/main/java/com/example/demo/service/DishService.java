package com.example.demo.service;

import com.example.demo.dao.api.IDishDao;
import com.example.demo.model.Dish;
import com.example.demo.service.api.IAppService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService implements IAppService<Dish> {
    private final IDishDao dishDao;

    public DishService(IDishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public Dish getById(long id) {
        return dishDao.findById(id).orElse(null);
    }

    @Override
    public Dish save(Dish model) {
        return dishDao.save(model);
    }

    @Override
    public List<Dish> getAll() {
        return dishDao.findAll();
    }

    @Override
    public Dish update(Dish model, long id) {
        Dish updateDish = getById(id);
        updateDish.setName(model.getName());
        updateDish.setUser(model.getUser());
        return save(updateDish);
    }

    @Override
    public void delete(long id) {
        dishDao.deleteById(id);
    }
}
