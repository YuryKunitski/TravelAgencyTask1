package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.ReviewDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Review;
import by.epam.kunitski.travelagency.exception.EntityNullValueRuntimeException;
import by.epam.kunitski.travelagency.service.ReviewService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    @Inject
    private ReviewDAOImpl reviewDAO;

    @Override
    public List<Review> findAll(Specification<Review> reviewSpecification) {
        return reviewDAO.getAll(reviewSpecification);
    }

    @Override
    public Optional<Review> findById(int id) {
        return reviewDAO.getById(id);
    }

    @Override
    public boolean delete(int id) {
        return reviewDAO.delete(id);
    }

    @Override
    public Review add(Review review) {
        if (review != null) {
            return reviewDAO.create(review);
        } else {
            throw new EntityNullValueRuntimeException("Method add() of " + this.getClass() + " got input value 'review' is null");
        }
    }

    @Override
    public Review update(Review review) {
        if (review != null) {
            return reviewDAO.update(review);
        } else {
            throw new EntityNullValueRuntimeException("Method update() of " + this.getClass() + " got input value 'review' is null");
        }
    }
}
