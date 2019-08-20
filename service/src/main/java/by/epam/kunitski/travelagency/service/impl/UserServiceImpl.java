package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.UserDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
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
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findUserByUsername(username);

        if (user != null) {

            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.roles(user.getRole().name());

            return builder.build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    @Override
    public List<User> findAll() {
        return userDAO.getAll();
    }

    @Override
    public List<User> findAllByCriteria(Specification<User> userSpecification) {
        return userDAO.getAllByCriteria(userSpecification);
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
