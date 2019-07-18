package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.CountryDAOImpl;
import by.epam.kunitski.travelagency.entity.Country;
import by.epam.kunitski.travelagency.service.EntityService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class CountryService implements EntityService<Country> {

    @Inject
    private CountryDAOImpl countryDAO;

    @Override
    public List<Country> findAll() {
        return countryDAO.getAll();
    }

    @Override
    public Optional<Country> findById(int id) {
        return countryDAO.getById(id);

    }

    @Override
    public boolean delete(int id) {
        if (countryDAO.getById(id).isPresent()) {
            return countryDAO.delete(id) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(Country entity) {
        if (entity == null || countryDAO.getById(entity.getId()).isPresent()) {
            return false;
        } else {
            return countryDAO.create(entity) > 0;
        }
    }

    @Override
    public Optional<Country> update(Country entity, int id) {
        if (entity != null) {
            return countryDAO.update(entity, id);
        } else {
            return Optional.empty();
        }

    }
}