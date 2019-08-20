package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.dao.entity.User;

public interface UserDAO extends EntityDAO<User> {

    User findUserByUsername(String username);
}
