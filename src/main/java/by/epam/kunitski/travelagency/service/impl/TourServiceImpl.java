package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.TourDAO;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.exception.EntityNullValueRuntimeException;
import by.epam.kunitski.travelagency.service.TourService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TourServiceImpl implements TourService {

    @Inject
    private TourDAO tourDAO;

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
    public Tour add(Tour tour) {
        if (tour != null) {
            return tourDAO.create(tour);
        } else {
            throw new EntityNullValueRuntimeException("Method add() of " + this.getClass() + " got input value 'tour' is null");
        }
    }

    @Override
    public Tour update(Tour tour, int id) {
        if (tour != null) {
            return tourDAO.update(tour, id).isPresent() ? tourDAO.update(tour, id).get() : new Tour();
        } else {
            throw new EntityNullValueRuntimeException("Method update() of " + this.getClass() + " got input value 'tour' is null");
        }
    }

}
