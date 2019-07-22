package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.config.TestConfig;
import by.epam.kunitski.travelagency.entity.Country;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryDAOImplTest {

    private Country expCountry = new Country(0, "Belarus");

    @Autowired
    private CountryDAOImpl countryDAO;

    @Autowired
    private
    Flyway flyway;

    @Before
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void getAll() {
        int sizeExpected = 25;
        int sizeActual = countryDAO.getAll().size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getById() {
        Country expectedCountry = new Country(1, "China");
        Country actualCountry = countryDAO.getById(1).get();
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    public void getByWrongId() {
        Optional<Country> actualCountry = countryDAO.getById(-1);
        assertEquals(Optional.empty(), actualCountry);
    }

    @Test
    public void delete() {
        int actual = countryDAO.delete(25);
        assertEquals(1, actual);
    }

    @Test
    public void deleteForWrongId() {
        int actual = countryDAO.delete(-1);
        assertEquals(0, actual);
    }

    @Test
    public void create() {
        Country actualCountry = countryDAO.create(expCountry);
        assertEquals(expCountry.getId(), actualCountry.getId());
    }

    @Test
    public void update() {
        Country expectedCountry = new Country(1, "Belarus");
        Country actualCountry = countryDAO.update(new Country(10, "Belarus"), 1).get();
        assertEquals(expectedCountry, actualCountry);
    }

}