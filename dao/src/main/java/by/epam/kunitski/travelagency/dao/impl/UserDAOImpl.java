package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.UserDAO;
import by.epam.kunitski.travelagency.dao.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends AbstractEntityDao<User> implements UserDAO {

    public UserDAOImpl() { super(User.class); }


}
