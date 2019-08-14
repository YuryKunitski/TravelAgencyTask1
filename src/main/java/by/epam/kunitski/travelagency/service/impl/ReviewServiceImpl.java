package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.ReviewDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Review;
import by.epam.kunitski.travelagency.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReviewServiceImpl implements ReviewService {

    private Set<ConstraintViolation<Review>> violationsReview;

    @Autowired
    private Validator validator;

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public List<Review> findAll(){
        return reviewDAO.getAll();
    }

    @Override
    public List<Review> findAllByCriteria(Specification<Review> reviewSpecification) {
        return reviewDAO.getAllByCriteria(reviewSpecification);
    }

    @Override
    public List<Review> findAllByUserId(int userId) {
        return reviewDAO.getAllByUserId(userId);
    }

    @Override
    public List<Review> findAllByTourId(int tourId) {
        return reviewDAO.getAllByTourId(tourId);
    }

    @Override
    public Optional<Review> findById(int id) {
        return reviewDAO.getById(id);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return reviewDAO.delete(id);
    }

    @Transactional
    @Override
    public Set<ConstraintViolation<Review>> add(Review review) {
        violationsReview = validator.validate(review);

        if (violationsReview.isEmpty()) {
            reviewDAO.create(review);
        }

        return violationsReview;
    }

    @Transactional
    @Override
    public Set<ConstraintViolation<Review>> update(Review review) {
        violationsReview = validator.validate(review);

        if (violationsReview.isEmpty()) {
            reviewDAO.update(review);
        }

        return violationsReview;
    }
}
