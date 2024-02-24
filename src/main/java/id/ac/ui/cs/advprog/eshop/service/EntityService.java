package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Entity;

import java.util.List;

public interface EntityService<T extends Entity> {
    T create(T entity);
    List<T> findAll();
    T findById(String entityId);
    void update(String entityId, T entity);
    void deleteById(String entityId);
}
