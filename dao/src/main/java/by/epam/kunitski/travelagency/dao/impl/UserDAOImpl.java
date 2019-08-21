package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.UserDAO;
import by.epam.kunitski.travelagency.dao.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractEntityDao<User> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }


    @Override
    public User findUserByUsername(String login) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicate = criteriaBuilder.equal(root.get("login"), login);

        criteriaQuery.where(criteriaBuilder.and(predicate));
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        List<User> userList = query.getResultList();

        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            return null;
        }
    }
}
