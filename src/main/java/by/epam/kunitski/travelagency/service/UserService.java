package by.epam.kunitski.travelagency.service;


import by.epam.kunitski.travelagency.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends EntityService<User> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(int id);

    @Override
    boolean delete(int id);

    @Override
    boolean add(User entity);

    @Override
    Optional<User> update(User entity, int id);

}
