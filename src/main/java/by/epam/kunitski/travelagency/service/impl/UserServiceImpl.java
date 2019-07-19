package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.UserDAOImpl;
import by.epam.kunitski.travelagency.entity.User;
import by.epam.kunitski.travelagency.service.UserService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDAOImpl userDAO;

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
    public boolean add(User user) {
        return userDAO.create(user) > 0;
    }

    @Override
    public Optional<User> update(User user, int id) {
        if (user != null) {
            return userDAO.update(user, id);
        } else {
            return Optional.empty();
        }
    }
}
