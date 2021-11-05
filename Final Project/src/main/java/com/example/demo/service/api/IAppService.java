package com.example.demo.service.api;

import com.example.demo.model.Product;

import java.util.List;

public interface IAppService<T> {
    T getById(long id);
    T save(T model);
    List<T> getAll();
    T update(T model, long id);
    void delete(long id);
}
