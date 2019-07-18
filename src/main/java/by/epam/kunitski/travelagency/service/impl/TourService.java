package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.TourDAOImpl;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.service.EntityService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TourService implements EntityService<Tour> {

    @Inject
    private TourDAOImpl tourDAO;

    @Override
    public List<Tour> findAll() {
        return tourDAO.getAll();
    }

    @Override
    public Optional<Tour> findById(int id) {
        return tourDAO.getById(id);
    }

    @Override
    public boolean delete(int id) {
        if (tourDAO.getById(id).isPresent()) {
            return tourDAO.delete(id) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(Tour entity) {
        if (entity == null || tourDAO.getById(entity.getId()).isPresent()) {
            return false;
        } else {
            return tourDAO.create(entity) > 0;
        }
    }

    @Override
    public Optional<Tour> update(Tour entity, int id) {
        if (entity != null) {
            return tourDAO.update(entity, id);
        } else {
            return Optional.empty();
        }
    }
}
