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
    public boolean add(Review entity) {
        if (entity == null || reviewDAO.getById(entity.getId()).isPresent()) {
            return false;
        } else {
            return reviewDAO.create(entity) > 0;
        }
    }

    @Override
    public Optional<Review> update(Review entity, int id) {
        if (entity != null) {
            return reviewDAO.update(entity, id);
        } else {
            return Optional.empty();
        }
    }
}
