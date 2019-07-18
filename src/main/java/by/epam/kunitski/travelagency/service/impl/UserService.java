package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.UserDAOImpl;
import by.epam.kunitski.travelagency.entity.User;
import by.epam.kunitski.travelagency.service.EntityService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class UserService implements EntityService<User> {

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
    public boolean add(User entity) {
        if (entity == null || userDAO.getById(entity.getId()).isPresent()) {
            return false;
        } else {
            return userDAO.create(entity) > 0;
        }
    }

    @Override
    public Optional<User> update(User entity, int id) {
        if (entity != null) {
            return userDAO.update(entity, id);
        } else {
            return Optional.empty();
        }
    }
}
