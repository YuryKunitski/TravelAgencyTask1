package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.UserDAO;
import by.epam.kunitski.travelagency.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Repository
public class UserDAOImpl extends AbstractEntityDao<User> implements UserDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return super.getAll(User.class);
    }

    @Override
    public Optional<User> getById(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;

        try {
            User user = getById(id).get();
            entityManager.remove(user);
            result = true;
        } catch (NoSuchElementException e) {
            LOGGER.error("Couldn't delete country with id" + id);
        }
        return result;
    }

    @Override
    public User create(User user) {
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }
}
