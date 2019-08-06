package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.EntityDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.entity.User;
import by.epam.kunitski.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private Set<ConstraintViolation<User>> violationsUser;

    @Autowired
    private Validator validator;

    @Autowired
    @Qualifier("userDAOImpl")
    private EntityDAO<User> userDAO;

    @Override
    public List<User> findAll(Specification<User> userSpecification) {
        return userDAO.getAll(userSpecification);
    }

    @Override
    public Optional<User> findById(int id) {
        return userDAO.getById(id);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return userDAO.delete(id);
    }

    @Transactional
    @Override
    public Set<ConstraintViolation<User>> add(User user) {
        violationsUser = validator.validate(user);

        if (violationsUser.isEmpty()) {
            userDAO.create(user);
        }

        return violationsUser;
    }

    @Transactional
    @Override
    public Set<ConstraintViolation<User>> update(User user) {
        violationsUser = validator.validate(user);

        if (violationsUser.isEmpty()) {
            userDAO.update(user);
        }

        return violationsUser;
    }

}
