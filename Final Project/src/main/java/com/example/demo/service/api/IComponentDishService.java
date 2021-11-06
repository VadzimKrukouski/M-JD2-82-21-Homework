package com.example.demo.service.api;

import com.example.demo.model.ComponentDish;

import java.util.List;

public interface IComponentDishService {
    ComponentDish getById(long id);
    ComponentDish save(ComponentDish model);
    List<ComponentDish> getAll();
    ComponentDish update(ComponentDish model, long id);
    void delete(long id);
}
