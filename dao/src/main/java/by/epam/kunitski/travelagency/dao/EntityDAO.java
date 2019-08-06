package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.dao.specification.Specification;

import java.util.List;
import java.util.Optional;

public interface EntityDAO<T> {

    List<T> getAll(Specification<T> specification);

    Optional<T> getById(int id);

    boolean delete(int id);

    T create(T entity);

    T update(T entity);

}
