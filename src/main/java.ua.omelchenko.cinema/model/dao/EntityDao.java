package model.dao;


public interface EntityDao<T> extends AutoCloseable {
    /*    List<T> getAll();
        int update (T entity);*/
    boolean create(T entity);

    T getById(int id);

    void close();
}
