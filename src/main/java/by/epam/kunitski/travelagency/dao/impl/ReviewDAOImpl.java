package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.ReviewDAO;
import by.epam.kunitski.travelagency.entity.Review;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ReviewDAOImpl extends AbstractEntityDao<Review> implements ReviewDAO {

    public ReviewDAOImpl() {
        super(Review.class);
    }

    @Override
    public List<Review> getAllByTourId(int tourId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Review> criteriaQuery = cb.createQuery(Review.class);
        Root<Review> root = criteriaQuery.from(Review.class);
        Join<Review, Tour> joinTour = root.join("tourID");
        Predicate predicateTour = cb.equal(joinTour.get("id"), tourId);
        criteriaQuery.where(cb.and(predicateTour));
        TypedQuery<Review> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<Review> getAllByUserId(int userId) {
        CriteriaBuilder cb = super.entityManager.getCriteriaBuilder();

        CriteriaQuery<Review> criteriaQuery = cb.createQuery(Review.class);
        Root<Review> root = criteriaQuery.from(Review.class);
        Join<Review, User> joinUser = root.join("userID");
        Predicate predicateUser = cb.equal(joinUser.get("id"), userId);

        criteriaQuery.where(cb.and(predicateUser));
        TypedQuery<Review> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

}
