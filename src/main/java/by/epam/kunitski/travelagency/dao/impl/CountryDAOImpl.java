package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.CountryDAO;
import by.epam.kunitski.travelagency.entity.Country;
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
public class CountryDAOImpl extends AbstractEntityDao<Country> implements CountryDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(CountryDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Country> getAll() {
        return super.getAll(entityManager, Country.class);
    }

    @Override
    public Optional<Country> getById(int id) {
        return Optional.ofNullable(entityManager.find(Country.class, id));
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;

        try {
            Country country = getById(id).get();
            entityManager.remove(country);
            result = true;
        } catch (NoSuchElementException e) {
            LOGGER.error("Couldn't delete country with id" + id);
        }
        return result;
    }

    @Override
    public Country create(Country country) {
        entityManager.persist(country);
        entityManager.flush();
        return country;
    }

    @Override
    public Country update(Country country) {
        return entityManager.merge(country);
    }
}
