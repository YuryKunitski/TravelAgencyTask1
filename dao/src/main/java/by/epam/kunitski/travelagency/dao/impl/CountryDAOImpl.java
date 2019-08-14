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

}
