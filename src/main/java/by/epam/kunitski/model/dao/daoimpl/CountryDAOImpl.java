package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.daointerface.CountryDAO;
import by.epam.kunitski.model.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryDAOImpl implements CountryDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(CountryDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL = "SELECT * FROM country";
    private static final String SQL_GET_BY_ID = "SELECT * FROM country WHERE id = ?";
    private static final String SQL_CREATE = "insert into country (name) values (?)";
    private static final String SQL_UPDATE = "update country set name = ? where id = ?;";
    private static final String SQL_DELETE = "delete from country where id = ?;";

    @Override
    public List<Country> getAll() {
        LOGGER.info("Start method getAll");
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_COUNTRY);
    }

    @Override
    public Optional<Country> getById(int id) {
        LOGGER.info("Start method getById");
        List<Country> countryList = jdbcTemplate.query(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_COUNTRY);
        return countryList.isEmpty() ? Optional.empty() : Optional.of(countryList.get(0));
    }

    @Override
    public int delete(int id) {
        LOGGER.info("Start method delete");
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public int create(Country country) {
        LOGGER.info("Start method create");
        return jdbcTemplate.update(SQL_CREATE, country.getName());
    }

    @Override
    public Optional<Country> update(Country country, int id) {
        LOGGER.info("Start method update");
        jdbcTemplate.update(SQL_UPDATE, country.getName(), id);
        return getById(id);
    }


}
