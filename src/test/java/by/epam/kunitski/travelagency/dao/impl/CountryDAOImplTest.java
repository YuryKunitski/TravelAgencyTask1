package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.CountryDAO;
import by.epam.kunitski.travelagency.dao.config.TestConfig;
import by.epam.kunitski.travelagency.dao.specification.impl.CountrySpecification;
import by.epam.kunitski.travelagency.entity.Country;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryDAOImplTest {

    private Country expCountry = new Country();

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private Flyway flyway;

    @Before
    public void init() {

        expCountry = InitEntity.initCountry();

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
    public void getAllByCriteria() {
        CountrySpecification countrySpecification = new CountrySpecification();
        int sizeExpected = 25;
        int sizeActual = countryDAO.getAllByCriteria(countrySpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }


    @Test
    public void getById() {

        expCountry.setId(1);
        expCountry.setName("China");

        Country actualCountry = countryDAO.getById(1).get();
        assertEquals(expCountry, actualCountry);
    }

    @Test
    public void getByWrongId() {
        Optional<Country> actualCountry = countryDAO.getById(-1);
        assertEquals(Optional.empty(), actualCountry);
    }

    @Transactional
    @Test
    public void delete() {
        assertTrue(countryDAO.delete(1));
    }

    @Transactional
    @Test
    public void deleteForWrongId() {
        assertFalse(countryDAO.delete(-1));
    }

    @Transactional
    @Test
    public void create() {
        Country actualCountry = countryDAO.create(expCountry);
        int generatedId = 26;
        assertEquals(generatedId, actualCountry.getId());
    }

    @Transactional
    @Test
    public void update() {
        expCountry.setId(10);
        Country actualCountry = countryDAO.update(expCountry);
        assertEquals(expCountry, actualCountry);
    }

}