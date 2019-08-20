package by.epam.kunitski.travelagency.dao.impl;


import by.epam.kunitski.travelagency.dao.config.DaoConfig;
import by.epam.kunitski.travelagency.dao.ReviewDAO;
import by.epam.kunitski.travelagency.dao.entity.Review;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = DaoConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ReviewDAOImplTest {

    private Review expReview = new Review();

    @Autowired
    private ReviewDAO reviewDAO;

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
    public void getAllByTourId() {
        int sizeExpected = 1;
        int sizeActual = reviewDAO.getAllByTourId(1).size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByUserId() {
        int sizeExpected = 10;
        int sizeActual = reviewDAO.getAllByUserId(1).size();
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

    @Transactional
    @Test
    public void delete() {
        assertTrue(reviewDAO.delete(1));
    }

    @Transactional
    @Test
    public void deleteForWrongId() {
        assertFalse(reviewDAO.delete(-1));
    }

    @Transactional
    @Test
    public void create() {
        Review actualReview = reviewDAO.create(expReview);
        int generatedId = 1001;
        assertEquals(generatedId, actualReview.getId());
    }

    @Transactional
    @Test
    public void update() {
        expReview.setId(10);
        Review reviewActual = reviewDAO.update(expReview);
        assertEquals(expReview, reviewActual);
    }

}