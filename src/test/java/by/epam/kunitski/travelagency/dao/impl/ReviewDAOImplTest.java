package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.EntityDAO;
import by.epam.kunitski.travelagency.dao.config.TestConfig;
import by.epam.kunitski.travelagency.entity.*;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Optional;

import static by.epam.kunitski.travelagency.entity.Hotel.FeatureType.CHILDREN_AREA;
import static by.epam.kunitski.travelagency.entity.Tour.TourType.ECONOM;
import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewDAOImplTest {

    private Review expReview = new Review();

    @Autowired
    @Qualifier("reviewDAOImpl")
    private EntityDAO<Review> reviewDAO;

    @Autowired
    private Flyway flyway;

    @Before
    public void init() {

        expReview = InitEntity.initReview();

        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void getAll() {
        int sizeExpected = 1000;
        int sizeActual = reviewDAO.getAll().size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getById() {
        expReview.setText("Pellentesque ultrices mattis odio.");
        Review actualUser = reviewDAO.getById(1).get();
        assertEquals(expReview.getText(), actualUser.getText());
    }

    @Test
    public void getByWrongId() {
        Optional<Review> reviewActual = reviewDAO.getById(-1);
        assertEquals(Optional.empty(), reviewActual);
    }

    @Test
    public void delete() {
       assertTrue(reviewDAO.delete(1));
    }

    @Test
    public void deleteForWrongId() {
       assertFalse(reviewDAO.delete(-1));
    }

    @Test
    public void create() {
        Review actualReview = reviewDAO.create(expReview);
        int generatedId = 1001;
        assertEquals(generatedId, actualReview.getId());
    }

    @Test
    public void update() {
        expReview.setId(10);
        Review reviewActual = reviewDAO.update(expReview);
        assertEquals(expReview, reviewActual);
    }

}