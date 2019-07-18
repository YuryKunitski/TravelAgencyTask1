package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.UserDAOImpl;
import by.epam.kunitski.travelagency.entity.User;
import by.epam.kunitski.travelagency.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class UserService implements EntityService<User> {

    private final static Logger LOGGER = LoggerFactory.getLogger(TourService.class);

    @Inject
    private UserDAOImpl userDAO;

    @Override
    public List<User> findAll() {
        LOGGER.info("Start method findAll");

        return userDAO.getAll();
    }

    @Override
    public Optional<User> findById(int id) {
        LOGGER.info("Start method findById");

        return userDAO.getById(id);
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start method delete");

        if (userDAO.getById(id).isPresent()) {
            return userDAO.delete(id) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(User entity) {
        LOGGER.info("Start method add");

        if (entity == null || userDAO.getById(entity.getId()).isPresent()) {
            return false;
        } else {
            return userDAO.create(entity) > 0;
        }
    }

    @Override
    public Optional<User> update(User entity, int id) {
        LOGGER.info("Start method update");

        if (entity != null) {
            return userDAO.update(entity, id);
        } else {
            return Optional.empty();
        }
    }
}
