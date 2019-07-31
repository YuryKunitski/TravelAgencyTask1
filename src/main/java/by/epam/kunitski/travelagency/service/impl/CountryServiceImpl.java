package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.CountryDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Country;
import by.epam.kunitski.travelagency.exception.EntityNullValueRuntimeException;
import by.epam.kunitski.travelagency.service.CountryService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {

    @Inject
    private CountryDAOImpl countryDAO;

    @Override
    public List<Country> findAll(Specification<Country> countrySpecification) {
        return countryDAO.getAll(countrySpecification);
    }

    @Override
    public Optional<Country> findById(int id) {
        return countryDAO.getById(id);
    }

    @Override
    public boolean delete(int id) {
        return countryDAO.delete(id);
    }

    @Override
    public Country add(Country country) {
        if (country != null) {
            return countryDAO.create(country);
        } else {
            throw new EntityNullValueRuntimeException("Method add() of " + this.getClass() + " got input value 'country' is null");
        }

    }

    @Override
    public Country update(Country country) {
        if (country != null) {
            return countryDAO.update(country);
        } else {
            throw new EntityNullValueRuntimeException("Method update() of " + this.getClass() + " got input value 'country' is null");
        }
    }

}