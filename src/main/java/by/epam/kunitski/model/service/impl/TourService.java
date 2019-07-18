package by.epam.kunitski.model.service.impl;

import by.epam.kunitski.model.dao.daoimpl.TourDAOImpl;
import by.epam.kunitski.model.entity.Tour;
import by.epam.kunitski.model.service.interf.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TourService implements EntityService<Tour> {

    private final static Logger LOGGER = LoggerFactory.getLogger(TourService.class);

    @Inject
    private TourDAOImpl tourDAO;

    @Override
    public List<Tour> findAll() {
        LOGGER.info("Start method findAll");

        return tourDAO.getAll();
    }

    @Override
    public Optional<Tour> findById(int id) {
        LOGGER.info("Start method findById");

        return tourDAO.getById(id);
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start method delete");

        if (tourDAO.getById(id).isPresent()) {
            return tourDAO.delete(id) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(Tour entity) {
        LOGGER.info("Start method add");

        if (entity == null || tourDAO.getById(entity.getId()).isPresent()) {
            return false;
        } else {
            return tourDAO.create(entity) > 0;
        }
    }

    @Override
    public Optional<Tour> update(Tour entity, int id) {
        LOGGER.info("Start method update");

        if (entity != null) {
            return tourDAO.update(entity, id);
        } else {
            return Optional.empty();
        }
    }
}
