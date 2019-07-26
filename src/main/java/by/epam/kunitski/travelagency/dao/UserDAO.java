package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> getAll();

    Optional<User> getById(int id);

    boolean delete(int id);

    User create(User user);

    User update(User user);
}
