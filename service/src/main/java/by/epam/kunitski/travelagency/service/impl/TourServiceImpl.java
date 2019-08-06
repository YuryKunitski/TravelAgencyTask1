package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.EntityDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Review;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TourServiceImpl implements TourService {

    private Set<ConstraintViolation<Tour>> violationsTour;

    @Autowired
    private Validator validator;

    @Autowired
    @Qualifier("tourDAOImpl")
    private EntityDAO<Tour> tourDAO;

    @Override
    public List<Tour> findAll(Specification<Tour> tourSpecification) {
        return tourDAO.getAll(tourSpecification);
    }

    @Override
    public Optional<Tour> findById(int id) {
        return tourDAO.getById(id);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return tourDAO.delete(id);
    }

    @Transactional
    @Override
    public Set<ConstraintViolation<Tour>> add(Tour tour) {
        violationsTour = validator.validate(tour);

        if (violationsTour.isEmpty()) {
            tourDAO.create(tour);
        }

        return violationsTour;
    }

    @Transactional
    @Override
    public Set<ConstraintViolation<Tour>> update(Tour tour) {
        violationsTour = validator.validate(tour);

        if (violationsTour.isEmpty()) {
            tourDAO.update(tour);
        }

        return violationsTour;
    }

}
