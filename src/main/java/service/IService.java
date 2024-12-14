package service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    T findByID(int id);

    void update(T t);
}
