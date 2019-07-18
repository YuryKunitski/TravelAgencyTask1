package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.CountryDAOImpl;
import by.epam.kunitski.travelagency.entity.Country;
import by.epam.kunitski.travelagency.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;


public class CountryService implements EntityService<Country> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Inject
    private CountryDAOImpl countryDAO;

    @Override
    public List<Country> findAll() {
        LOGGER.info("Start method findAll");

        return countryDAO.getAll();
    }

    @Override
    public Optional<Country> findById(int id) {
        LOGGER.info("Start method findById");

        return countryDAO.getById(id);

    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start method delete");

        if (countryDAO.getById(id).isPresent()) {
            return countryDAO.delete(id) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(Country entity) {
        LOGGER.info("Start method add");

        if (entity == null || countryDAO.getById(entity.getId()).isPresent()) {
            return false;
        } else {
            return countryDAO.create(entity) > 0;
        }
    }

    @Override
    public Optional<Country> update(Country entity, int id) {
        LOGGER.info("Start method update");

        if (entity != null) {
            return countryDAO.update(entity, id);
        } else {
            return Optional.empty();
        }

    }
}