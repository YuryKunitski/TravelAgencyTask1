package by.epam.kunitski.travelagency.service;


import by.epam.kunitski.travelagency.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends EntityService<User> {

    List<User> findAll();

    Optional<User> findById(int id);

    boolean delete(int id);

    boolean add(User entity);

    Optional<User> update(User entity, int id);

}
