package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.ReviewDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Review;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewDAOImpl extends AbstractEntityDao<Review> implements ReviewDAO {

    public ReviewDAOImpl() {
        super(Review.class);
    }

    @Override
    public List<Review> getAll() {
        return super.getAll();
    }

    @Override
    public List<Review> getAllByCriteria(Specification<Review> reviewSpecification) {
        return super.getAllByCriteria(reviewSpecification);
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

    @Override
    public Optional<Review> getById(int id) {
        return super.getById(id);
    }

    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    public Review create(Review review) {
        return super.create(review);
    }

    @Override
    public Review update(Review review) {
        return super.update(review);
    }
}
