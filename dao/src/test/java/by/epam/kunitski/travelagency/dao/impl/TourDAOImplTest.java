package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.TourDAO;
import by.epam.kunitski.travelagency.dao.config.DaoConfig;
import by.epam.kunitski.travelagency.dao.daoForm.TourSearchForm;
import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = DaoConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class TourDAOImplTest {

    private Tour expTour = new Tour();
    private TourSearchForm tourSearchForm = new TourSearchForm();

    @Autowired
    private TourDAO tourDAO;

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
    public void getAllByUserId() {
        int sizeExpected = 4;
        int sizeActual = tourDAO.getAllByUserId(1).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByCountryNames() {
        List<String> countryList = new ArrayList<>();
        countryList.add("Belarus");
        countryList.add("Sweden");

        tourSearchForm.setCountryNames(countryList);
        TourSpecification tourSpecification = new TourSpecification(tourSearchForm);

        int sizeExpected = 80;
        int sizeActual = tourDAO.getAllByCriteria(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByTourType() {
        tourSearchForm.setTourType(Tour.TourType.ALL_INCLUSIVE);
        TourSpecification tourSpecification = new TourSpecification(tourSearchForm);

        int sizeExpected = 322;
        int sizeActual = tourDAO.getAllByCriteria(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinMaxStars() {
        tourSearchForm.setMinStars(4);
        tourSearchForm.setMaxStars(5);
        TourSpecification tourSpecification = new TourSpecification(tourSearchForm);

        int sizeExpected = 500;
        int sizeActual = tourDAO.getAllByCriteria(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinStars() {
        tourSearchForm.setMinStars(5);
        TourSpecification tourSpecification = new TourSpecification(tourSearchForm);

        int sizeExpected = 250;
        int sizeActual = tourDAO.getAllByCriteria(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinMaxDate() {
        tourSearchForm.setMinDate(LocalDate.of(2019, 8, 5));
        tourSearchForm.setMaxDate(LocalDate.of(2019, 8, 25));
        TourSpecification tourSpecification = new TourSpecification(tourSearchForm);

        int sizeExpected = 53;
        int sizeActual = tourDAO.getAllByCriteria(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinDate() {
        tourSearchForm.setMinDate(LocalDate.of(2019, 8, 25));
        TourSpecification tourSpecification = new TourSpecification(tourSearchForm);

        int sizeExpected = 368;
        int sizeActual = tourDAO.getAllByCriteria(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinMaxDuration() {
        tourSearchForm.setMinDuration(7);
        tourSearchForm.setMaxDuration(11);
        TourSpecification tourSpecification = new TourSpecification(tourSearchForm);

        int sizeExpected = 200;
        int sizeActual = tourDAO.getAllByCriteria(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMaxDuration() {
        tourSearchForm.setMaxDuration(11);
        TourSpecification tourSpecification = new TourSpecification(tourSearchForm);

        int sizeExpected = 440;
        int sizeActual = tourDAO.getAllByCriteria(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinMaxCost() {
        tourSearchForm.setMinCost(500.0);
        tourSearchForm.setMaxCost(700.0);
        TourSpecification tourSpecification = new TourSpecification(tourSearchForm);

        int sizeExpected = 19;
        int sizeActual = tourDAO.getAllByCriteria(tourSpecification).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByMinCost() {
        tourSearchForm.setMaxCost(1000.0);
        TourSpecification tourSpecification = new TourSpecification(tourSearchForm);

        int sizeExpected = 94;
        int sizeActual = tourDAO.getAllByCriteria(tourSpecification).size();
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