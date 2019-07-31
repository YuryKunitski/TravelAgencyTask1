package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.EntityDAO;
import by.epam.kunitski.travelagency.dao.config.TestConfig;
import by.epam.kunitski.travelagency.entity.Tour;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TourDAOImplTest {

    private Tour expTour = new Tour();

    @Autowired
    @Qualifier("tourDAOImpl")
    private EntityDAO<Tour> tourDAO;

    @Autowired
    private
    Flyway flyway;

    @Before
    public void init() {

        expTour = InitEntity.initTour();

        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void getAll() {
        int sizeExpected = 1000;
        int sizeActual = tourDAO.getAll().size();
        assertEquals(sizeExpected, sizeActual);
    }


    @Test
    public void getById() {

        expTour.setPhoto("http://dummyimage.com/190x216.jpg/ff4444/ffffff");

        Tour actualTour = tourDAO.getById(1).get();
        assertEquals(expTour.getPhoto(), actualTour.getPhoto());
    }

    @Test
    public void getByWrongId() {
        Optional<Tour> actualTour = tourDAO.getById(-1);
        assertEquals(Optional.empty(), actualTour);
    }

    @Test
    public void delete() {
        assertTrue(tourDAO.delete(1));
    }

    @Test
    public void deleteForWrongId() {
        assertFalse(tourDAO.delete(-1));
    }

    @Test
    public void create() {
        Tour actualTour = tourDAO.create(expTour);
        int generatedId = 1001;
        assertEquals(generatedId, actualTour.getId());
    }

    @Test
    public void update() {
        expTour.setId(10);
        Tour tourActual = tourDAO.update(expTour);
        assertEquals(expTour, tourActual);
    }
}