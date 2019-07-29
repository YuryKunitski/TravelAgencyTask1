package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.ReviewDAO;
import by.epam.kunitski.travelagency.entity.Hotel;
import by.epam.kunitski.travelagency.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Repository
public class ReviewDAOImpl implements ReviewDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(ReviewDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Review> getAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery = builder.createQuery(Review.class);
        Root<Review> root = criteriaQuery.from(Review.class);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<Review> getById(int id) {
        return Optional.ofNullable(entityManager.find(Review.class, id));
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;

        try {
            Review review = getById(id).get();
            entityManager.remove(review);
            result = true;
        } catch (NoSuchElementException e) {
            LOGGER.error("Couldn't delete review with id" + id);
        }
        return result;
    }

    @Override
    public Review create(Review review) {
        entityManager.persist(review);
        entityManager.flush();
        return review;
    }

    @Override
    public Review update(Review review) {
        return entityManager.merge(review);
    }

}
