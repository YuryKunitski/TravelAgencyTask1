package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.CountryDAO;
import by.epam.kunitski.travelagency.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryDAOImpl implements CountryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Country> ROW_MAPPER_COUNTRY;

    private static final String SQL_GET_ALL = "SELECT * FROM country";
    private static final String SQL_GET_BY_ID = "SELECT * FROM country WHERE id = ?";
    private static final String SQL_CREATE = "insert into country (name) values (?)";
    private static final String SQL_UPDATE = "update country set name = ? where id = ?;";
    private static final String SQL_DELETE = "delete from country where id = ?;";

    @Override
    public List<Country> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_COUNTRY);
    }

    @Override
    public Optional<Country> getById(int id) {
        List<Country> countryList = jdbcTemplate.query(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_COUNTRY);
        return countryList.isEmpty() ? Optional.empty() : Optional.of(countryList.get(0));
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public int create(Country country) {
        return jdbcTemplate.update(SQL_CREATE, country.getName());
    }

    @Override
    public Optional<Country> update(Country country, int id) {
        jdbcTemplate.update(SQL_UPDATE, country.getName(), id);
        return getById(id);
    }
}
