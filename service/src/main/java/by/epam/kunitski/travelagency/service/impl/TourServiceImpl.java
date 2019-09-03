package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.TourDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TourServiceImpl implements TourService {

    private Set<ConstraintViolation<Tour>> violationsTour;

    @Autowired
    private Validator validator;

    @Autowired
    private TourDAO tourDAO;

    @Override
    public List<Tour> findAll() {
        return tourDAO.getAll();
    }

    @Override
    public List<Tour> findAllByCriteria(Specification<Tour> tourSpecification) {
        return tourDAO.getAllByCriteria(tourSpecification);
    }

    @Override
    public List<Tour> findAllByUserId(int userId) {
        return tourDAO.getAllByUserId(userId);
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

    @Transactional
    @Override
    public void uploadTours(Tour[] tours) {

        for (Tour tour : tours) {
            tourDAO.create(tour);
        }

    }

    @Override
    public Page<Tour> findPaginated(Pageable pageable, Specification<Tour> tourSpecification) {

        List<Tour> tours = findAllByCriteria(tourSpecification);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Tour> list;

        if (tours.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, tours.size());
            list = tours.subList(startItem, toIndex);
        }

        Page<Tour> tourPage = new PageImpl<Tour>(list, PageRequest.of(currentPage, pageSize), tours.size());
        return tourPage;
    }


}
