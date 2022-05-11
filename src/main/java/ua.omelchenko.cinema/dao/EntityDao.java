package ua.omelchenko.cinema.dao;


public interface EntityDao<T> extends AutoCloseable {
    boolean create(T entity);

    T getById(int id);

    void close();
}
