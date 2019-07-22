package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.CountryDAO;
import by.epam.kunitski.travelagency.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class CountryDAOImpl implements CountryDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(CountryDAOImpl.class);

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

        Optional<Country> countryOptional = null;

        try {
           countryOptional = Optional.of(jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_COUNTRY));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Couldn't find country with id " + id);
            countryOptional = Optional.empty();
        }
        return countryOptional;
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public Country create(Country country) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pst =
                                con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                        pst.setString(1, country.getName());
                        return pst;
                    }
                },
                keyHolder);
        if (keyHolder.getKey() != null) {
            country.setId(keyHolder.getKey().intValue());
        }
        return country;
    }

    @Override
    public Optional<Country> update(Country country, int id) {
        jdbcTemplate.update(SQL_UPDATE, country.getName(), id);
        return getById(id);
    }
}
