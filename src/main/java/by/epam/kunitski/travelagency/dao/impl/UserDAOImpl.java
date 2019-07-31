package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userDAOImpl")
public class UserDAOImpl extends AbstractEntityDao<User> {

    public UserDAOImpl(){
        super(User.class);
    }

    @Override
    public List<User> getAll(Specification<User> userSpecification) {
        return super.getAll(userSpecification);
    }

    @Override
    public Optional<User> getById(int id) {
        return super.getById(id);
    }

    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public User update(User user) {
        return super.update(user);
    }
}
