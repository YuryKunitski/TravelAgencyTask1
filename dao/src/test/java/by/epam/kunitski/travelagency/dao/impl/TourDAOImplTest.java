package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.EntityDAO;
import by.epam.kunitski.travelagency.dao.config.DaoConfig;
import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.entity.Tour;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@ContextConfiguration(classes = DaoConfig.class)
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

        int sizeExpected = 1000;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByCountryNames() {
        List<String> countryList = new ArrayList<>();
        countryList.add("Belarus");
        countryList.add("Sweden");

        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setCountryNames(countryList);

        int sizeExpected = 80;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByTourType() {
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setTourType(Tour.TourType.ALL_INCLUSIVE);

        int sizeExpected = 322;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinMaxStars() {
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMinStars(4);
        tourSpecification.setMaxStars(5);

        int sizeExpected = 500;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinStars() {
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMinStars(5);

        int sizeExpected = 250;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinMaxDate() {
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMinDate(LocalDate.of(2019, 8, 5));
        tourSpecification.setMaxDate(LocalDate.of(2019, 8, 25));

        int sizeExpected = 54;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinDate() {
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMinDate(LocalDate.of(2019, 8, 25));

        int sizeExpected = 367;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinMaxDuration() {
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMinDuration(7);
        tourSpecification.setMaxDuration(11);

        int sizeExpected = 200;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMaxDuration() {
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMaxDuration(11);

        int sizeExpected = 440;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinMaxCost() {
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMinCost(500.0);
        tourSpecification.setMaxCost(700.0);

        int sizeExpected = 19;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinCost() {
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMaxCost(1000.0);

        int sizeExpected = 94;
        int sizeActual = tourDAO.getAll(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByUserId() {
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setUserID(11);

        int sizeExpected = 10;
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