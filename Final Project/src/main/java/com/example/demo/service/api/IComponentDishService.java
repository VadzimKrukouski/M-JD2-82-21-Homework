package com.example.demo.service.api;

import com.example.demo.model.ComponentDish;

import java.util.List;

public interface IComponentDishService {
    ComponentDish getById(long id);
    ComponentDish save(ComponentDish componentDish);
    List<ComponentDish> getAll();
    ComponentDish update(ComponentDish componentDish, long id);
    void delete(long id);
}
