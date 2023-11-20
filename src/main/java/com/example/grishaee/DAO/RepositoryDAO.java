package com.example.grishaee.DAO;

import java.util.List;
public interface RepositoryDAO<T> {

    public Long insert(T o) throws Exception;

    public void update(T o);

    public void delete(Long Id);

    public T findById(Long Id);

    public List<T> findAll();

}
