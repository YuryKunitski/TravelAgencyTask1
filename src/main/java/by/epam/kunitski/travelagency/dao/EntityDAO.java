package by.epam.kunitski.travelagency.dao;

import java.util.List;
import java.util.Optional;

public interface EntityDAO<T> {

    List<T> getAll();

    Optional<T> getById(int id);

    boolean delete(int id);

    T create(T entity);

    T update(T entity);

}
