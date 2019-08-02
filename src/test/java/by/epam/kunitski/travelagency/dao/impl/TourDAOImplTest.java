package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.EntityDAO;
import by.epam.kunitski.travelagency.dao.config.TestConfig;
import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.entity.Tour;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
        TourSpecification tourSpecification = new TourSpecification();
//        tourSpecification.setUserID(111);

        int sizeExpected = 1000;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }


    @Test
    public void getById() {

        expTour.setId(1);

        Tour actualTour = tourDAO.getById(1).get();
        assertEquals(expTour, actualTour);
    }

    @Test
    public void getByWrongId() {
        Optional<Tour> actualTour = tourDAO.getById(-1);
        assertEquals(Optional.empty(), actualTour);
    }

    @Transactional
    @Test
    public void delete() {
        assertTrue(tourDAO.delete(1));
    }

    @Transactional
    @Test
    public void deleteForWrongId() {
        assertFalse(tourDAO.delete(-1));
    }

    @Transactional
    @Test
    public void create() {
        Tour actualTour = tourDAO.create(expTour);
        int generatedId = 1001;
        assertEquals(generatedId, actualTour.getId());
    }

    @Transactional
    @Test
    public void update() {
        expTour.setId(10);
        Tour tourActual = tourDAO.update(expTour);
        assertEquals(expTour, tourActual);
    }
}