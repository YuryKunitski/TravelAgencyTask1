package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.TourDAOImpl;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.service.TourService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TourServiceImpl implements TourService {

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
    public boolean add(Tour tour) {
        return tourDAO.create(tour) > 0;
    }

    @Override
    public Optional<Tour> update(Tour tour, int id) {
        if (tour != null) {
            return tourDAO.update(tour, id);
        } else {
            return Optional.empty();
        }
    }
}
