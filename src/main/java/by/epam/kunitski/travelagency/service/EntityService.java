package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.dao.specification.Specification;

import java.util.List;
import java.util.Optional;

public interface EntityService<T> {

    List<T> findAll(Specification<T> specification);

    Optional<T> findById(int id);

    boolean delete(int id);

    T add(T entity);

    T update(T entity, int id);
}
