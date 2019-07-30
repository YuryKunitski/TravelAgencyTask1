package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.UserDAOImpl;
import by.epam.kunitski.travelagency.entity.User;
import by.epam.kunitski.travelagency.exception.EntityNullValueRuntimeException;
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
        return userDAO.delete(id);
    }

    @Override
    public User add(User user) {
        if (user != null) {
            return userDAO.create(user);
        } else {
            throw new EntityNullValueRuntimeException("Method add() of " + this.getClass() + " got input value 'user' is null");
        }
    }

    @Override
    public User update(User user, int id) {
        if (user != null) {
            return userDAO.update(user);
        } else {
            throw new EntityNullValueRuntimeException("Method update() of " + this.getClass() + " got input value 'user' is null");
        }
    }

}
