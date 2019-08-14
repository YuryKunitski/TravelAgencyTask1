package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.TourDAO;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository

public class TourDAOImpl extends AbstractEntityDao<Tour> implements TourDAO {

    public TourDAOImpl(){
        super(Tour.class);
    }

    @Override
    public List<Tour> getAllByUserId(int userId){
        CriteriaBuilder cb = super.entityManager.getCriteriaBuilder();

        CriteriaQuery<Tour> criteriaQuery = cb.createQuery(Tour.class);
        Root<Tour> root = criteriaQuery.from(Tour.class);
        Join<Tour, User> joinUser = root.join("userList");
        Predicate predicateUser = cb.equal(joinUser.get("id"), userId);

        criteriaQuery.where(cb.and(predicateUser));
        TypedQuery<Tour> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

}


