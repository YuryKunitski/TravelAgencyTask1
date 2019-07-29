package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.TourDAO;
import by.epam.kunitski.travelagency.entity.Review;
import by.epam.kunitski.travelagency.entity.Tour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Repository
public class TourDAOImpl implements TourDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(TourDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tour> getAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery = builder.createQuery(Tour.class);
        Root<Tour> root = criteriaQuery.from(Tour.class);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<Tour> getById(int id) {
        return Optional.ofNullable(entityManager.find(Tour.class, id));

    }

    @Override
    public boolean delete(int id) {
        boolean result = false;

        try {
            Tour tour = getById(id).get();
            entityManager.remove(tour);
            result = true;
        } catch (NoSuchElementException e) {
            LOGGER.error("Couldn't delete tour with id" + id);
        }
        return result;
    }

    @Override
    public Tour create(Tour tour) {
        entityManager.persist(tour);
        entityManager.flush();
        return tour;
    }

    @Override
    public Tour update(Tour tour) {
        return entityManager.merge(tour);
    }
}
