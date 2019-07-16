package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.daointerface.CountryDAO;
import by.epam.kunitski.model.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryDAOImpl implements CountryDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL = "SELECT * FROM country";
    private static final String SQL_GET_BY_ID = "SELECT * FROM country WHERE id = ?";
    private static final String SQL_CREATE = "insert into country (name) values (?)";
    private static final String SQL_UPDATE = "update country set name = ? where id = ?;";
    private static final String SQL_DELETE = "delete from country where id = ?;";

    @Override
    public List<Country> getAll() {
        LOGGER.info("CountryDAOImpl | getAll");
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_COUNTRY);
    }

    @Override
    public Country getById(int id) {
        LOGGER.info("CountryDAOImpl | getById");
        Country country = null;
        try {
            country = (Country) jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_COUNTRY);
        } catch (DataAccessException e) {
            LOGGER.error("Couldn't find entity of type Country with id " + id);
        }
        return country;
    }

    @Override
    public int delete(int id) {
        LOGGER.info("CountryDAOImpl | delete");
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public boolean create(Country country) {
        LOGGER.info("CountryDAOImpl | create");
        if (country != null) {
            jdbcTemplate.update(SQL_CREATE, country.getName());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Country update(Country country, int id) {
        LOGGER.info("CountryDAOImpl | update");
        if (country != null) {
            jdbcTemplate.update(SQL_UPDATE, country.getName(), id);
            return getById(id);
        } else return null;
    }


}
