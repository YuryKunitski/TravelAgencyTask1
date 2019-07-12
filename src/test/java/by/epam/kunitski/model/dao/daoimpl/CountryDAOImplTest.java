package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.dbconfig.TestConfig;
import by.epam.kunitski.model.entity.Country;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryDAOImplTest {

    private Country expectedCountry;

    @Autowired
    private CountryDAOImpl countryDAO;

    @Autowired
    Flyway flyway;

    @Before
    public void init(){
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
        expectedCountry = new Country(1, "China");
        Country actualCountry = countryDAO.getById(1);
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    public void getByWrongId() {
        Country actualCountry = countryDAO.getById(-1);
        assertEquals(null, actualCountry);
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
        boolean actualResult = countryDAO.create(new Country(1, "Belarus"));
        assertEquals(true, actualResult);
    }

    @Test
    public void createCountryNull() {
        boolean actualResult = countryDAO.create(null);
        assertEquals(false, actualResult);
    }

    @Test
    public void update() {
        expectedCountry = new Country(1, "Belarus");
        Country actualCountry = countryDAO.update(new Country(10, "Belarus"), 1);
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    public void updateForWrongId() {
        Country actualCountry = countryDAO.update(new Country(10, "Belarus"), -1);
        assertEquals(null, actualCountry);
    }

    @Test
    public void updateForCountryNull() {
        Country actualCountry = countryDAO.update(null, -1);
        assertEquals(null, actualCountry);
    }
}