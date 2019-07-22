package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.config.TestConfig;
import by.epam.kunitski.travelagency.entity.Tour;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Optional;

import static by.epam.kunitski.travelagency.entity.Tour.TourType.ONLY_BREAKFAST;
import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TourDAOImplTest {

    private Tour expTour = new Tour(1, "http://dummyimage.com/147x238.jpg/cc0000/ffffff", LocalDate.of(2019, 01, 15)
            , 3, "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.", 231.70, 1, 1, ONLY_BREAKFAST);

    @Autowired
    private TourDAOImpl tourDAO;

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
        int sizeExpected = 1000;
        int sizeActual = tourDAO.getAll().size();
        assertEquals(sizeExpected, sizeActual);
    }


    @Test
    public void getById() {
        Tour actualTour = tourDAO.getById(1).get();
        assertEquals(expTour, actualTour);
    }

    @Test
    public void getByWrongId() {
        Optional<Tour> actualTour = tourDAO.getById(-1);
        assertEquals(Optional.empty(), actualTour);
    }

    @Test
    public void delete() {
        int actual = tourDAO.delete(1);
        assertEquals(1, actual);
    }

    @Test
    public void deleteForWrongId() {
        int actual = tourDAO.delete(-1);
        assertEquals(0, actual);
    }

    @Test
    public void create() {
        Tour actualTour = tourDAO.create(expTour);
        assertEquals(expTour, actualTour);
    }

    @Test
    public void update() {
        Tour tourActual = tourDAO.update(expTour, 1).get();
        assertEquals(expTour, tourActual);
    }

//    @Test
//    public void updateForWrongId() {
//        Tour tourActual = tourDAO.update(expTour, -1);
//
//        assertEquals(Optional.empty(), tourActual);
//    }
}