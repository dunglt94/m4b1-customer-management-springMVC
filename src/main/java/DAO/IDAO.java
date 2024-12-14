package DAO;

import java.util.List;

public interface IDAO<T> {
    List<T> findAll();

    T findByID(int id);

    void update(T t);
}
