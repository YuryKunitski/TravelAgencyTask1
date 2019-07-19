package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.CountryDAOImpl;
import by.epam.kunitski.travelagency.entity.Country;
import by.epam.kunitski.travelagency.service.CountryService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {

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
    public boolean add(Country country) {
        return countryDAO.create(country) > 0;

    }

    @Override
    public Optional<Country> update(Country country, int id) {
        if (country != null) {
            return countryDAO.update(country, id);
        } else {
            return Optional.empty();
        }

    }
}