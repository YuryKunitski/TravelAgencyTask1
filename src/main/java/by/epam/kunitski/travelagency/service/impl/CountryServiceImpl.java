package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.EntityDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Country;
import by.epam.kunitski.travelagency.service.CountryService;
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
public class CountryServiceImpl implements CountryService {


    private Set<ConstraintViolation<Country>> violationsCountry;

    @Autowired
    private Validator validator;

    @Autowired
    @Qualifier("countryDAOImpl")
    private EntityDAO<Country> countryDAO;

    @Override
    public List<Country> findAll(){
     return countryDAO.getAll();
    }

    @Override
    public List<Country> findAllByCriteria(Specification<Country> countrySpecification) {
        return countryDAO.getAllByCriteria(countrySpecification);
    }

    @Override
    public Optional<Country> findById(int id) {
        return countryDAO.getById(id);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return countryDAO.delete(id);
    }

    @Transactional
    @Override
    public Set<ConstraintViolation<Country>> add(Country country) {
        violationsCountry = validator.validate(country);

        if (violationsCountry.isEmpty()) {
            countryDAO.create(country);
        }

        return violationsCountry;
    }

    @Transactional
    @Override
    public Set<ConstraintViolation<Country>> update(Country country) {
        violationsCountry = validator.validate(country);

        if (violationsCountry.isEmpty()){
            countryDAO.update(country);
        }

        return violationsCountry;
    }

}