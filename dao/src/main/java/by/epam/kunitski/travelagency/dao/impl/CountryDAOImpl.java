package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.CountryDAO;
import by.epam.kunitski.travelagency.dao.entity.Country;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDAOImpl extends AbstractEntityDao<Country> implements CountryDAO {

    public CountryDAOImpl() {
        super(Country.class);
    }

}
