package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.UserDAO;
import by.epam.kunitski.travelagency.entity.User;
import by.epam.kunitski.travelagency.service.UserService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.getAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public boolean delete(int id) {
        if (userDAO.getById(id).isPresent()) {
            return userDAO.delete(id) > 0;
        } else {
            return false;
        }
    }

    @Override
    public User add(User user) {
        if (user != null) {
            return userDAO.create(user);
        } else {
            return new User();
        }
    }

    @Override
    public User update(User user, int id) {
        if (user != null) {
            return userDAO.update(user, id).isPresent() ? userDAO.update(user, id).get() : new User();
        } else {
            return new User();
        }
    }

}
