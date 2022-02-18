package model.dao;

import java.util.List;

public interface EntityDao<T> extends AutoCloseable {
    /*    List<T> getAll();
        T getById(int id);
        int update (T entity);*/
    boolean create(T entity);

    void close();
}
