package com.Kadir.recipeWebsite.Services;

import java.util.List;

public interface IBaseService <T>{
    public T add(T entity);
    public List<T> getAll();
    public T getById();
    public T update(T entity, Long id);
    public boolean delete(Long id);
}
