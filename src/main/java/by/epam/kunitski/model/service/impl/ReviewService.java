package by.epam.kunitski.model.service.impl;

import by.epam.kunitski.model.dao.daoimpl.ReviewDAOImpl;
import by.epam.kunitski.model.entity.Review;
import by.epam.kunitski.model.service.interf.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ReviewService implements EntityService<Review> {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReviewService.class);

    @Inject
    private ReviewDAOImpl reviewDAO;

    @Override
    public List<Review> findAll() {
        LOGGER.info("Start method findAll");

        return reviewDAO.getAll();
    }

    @Override
    public Optional<Review> findById(int id) {
        LOGGER.info("Start method findById");

        return reviewDAO.getById(id);
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start method delete");

        if (reviewDAO.getById(id).isPresent()) {
            return reviewDAO.delete(id) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(Review entity) {
        LOGGER.info("Start method add");

        if (entity == null || reviewDAO.getById(entity.getId()).isPresent()) {
            return false;
        } else {
            return reviewDAO.create(entity) > 0;
        }
    }

    @Override
    public Optional<Review> update(Review entity, int id) {
        LOGGER.info("Start method update");

        if (entity != null) {
            return reviewDAO.update(entity, id);
        } else {
            return Optional.empty();
        }
    }
}
