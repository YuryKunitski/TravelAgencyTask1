package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.CountryDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Country;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryDAOImpl extends AbstractEntityDao<Country> implements CountryDAO {

    public CountryDAOImpl() {
        super(Country.class);
    }

    @Override
    public List<Country> getAll() {
        return super.getAll();
    }

    @Override
    public List<Country> getAllByCriteria(Specification<Country> countrySpecification) {
        return super.getAllByCriteria(countrySpecification);
    }

    @Override
    public Optional<Country> getById(int id) {
        return super.getById(id);
    }

    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    public Country create(Country country) {
        return super.create(country);
    }

    @Override
    public Country update(Country country) {
        return super.update(country);
    }
}
