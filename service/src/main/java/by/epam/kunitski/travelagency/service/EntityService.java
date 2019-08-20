package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.dao.specification.Specification;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EntityService<T> {

    List<T> findAll();

    List<T> findAllByCriteria(Specification<T> specification);

    Optional<T> findById(int id);

    boolean delete(int id);

    Set<ConstraintViolation<T>> add(T entity);

    Set<ConstraintViolation<T>> update(T entity);
}
