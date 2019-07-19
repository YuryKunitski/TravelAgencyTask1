package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> getAll();

    Optional<User> getById(int id);

    int delete(int id);

    int create(User user);

    Optional<User> update(User user, int id);
}
