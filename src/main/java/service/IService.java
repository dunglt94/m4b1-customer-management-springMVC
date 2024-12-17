package service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    void create(T object);

    T findByID(int id);

    void update(T t);

    void remove(int id);
}
