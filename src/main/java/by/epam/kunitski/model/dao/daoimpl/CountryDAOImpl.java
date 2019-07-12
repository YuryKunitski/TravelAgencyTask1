package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.daointerface.CountryDAO;
import by.epam.kunitski.model.dao.dbconfig.DBConfig;
import by.epam.kunitski.model.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryDAOImpl implements CountryDAO {

    private static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public Country getById(int id) {
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
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public boolean create(Country country) {
        if (country != null) {
            jdbcTemplate.update(SQL_CREATE, country.getName());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Country update(Country country, int id) {
        if (country != null) {
            jdbcTemplate.update(SQL_UPDATE, country.getName(), id);
            return getById(id);
        } else return null;
    }


    public static void main(String[] args) {

        Country country = new Country(26, "Zimbabve");

        ApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
        CountryDAO countryDAO = context.getBean(CountryDAOImpl.class);
        System.out.println(countryDAO.delete(151));
    }

}
