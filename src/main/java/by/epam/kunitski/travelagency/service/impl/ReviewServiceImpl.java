package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.ReviewDAOImpl;
import by.epam.kunitski.travelagency.entity.Review;
import by.epam.kunitski.travelagency.service.ReviewService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    @Inject
    private ReviewDAOImpl reviewDAO;

    @Override
    public List<Review> findAll() {
        return reviewDAO.getAll();
    }

    @Override
    public Optional<Review> findById(int id) {
        return reviewDAO.getById(id);
    }

    @Override
    public boolean delete(int id) {
        if (reviewDAO.getById(id).isPresent()) {
            return reviewDAO.delete(id) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(Review review) {
        return reviewDAO.create(review) > 0;
    }

    @Override
    public Optional<Review> update(Review review, int id) {
        if (review != null) {
            return reviewDAO.update(review, id);
        } else {
            return Optional.empty();
        }
    }
}
