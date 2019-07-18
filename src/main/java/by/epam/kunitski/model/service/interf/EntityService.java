package by.epam.kunitski.model.service.interf;

import java.util.List;
import java.util.Optional;

public interface EntityService<T> {

    List<T> findAll();

    Optional<T> findById(int id);

    boolean delete(int id);

    boolean add(T entity);

    Optional<T> update(T entity, int id);

}
