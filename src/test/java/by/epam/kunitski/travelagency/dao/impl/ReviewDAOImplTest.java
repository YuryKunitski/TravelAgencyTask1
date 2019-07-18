package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.dbconfig.TestConfig;
import by.epam.kunitski.travelagency.entity.Review;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewDAOImplTest {

    private Optional<Review> expectedReview;

    @Autowired
    private ReviewDAOImpl reviewDAO;

    @Autowired
    Flyway flyway;

    @Before
    public void init() {
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
        expectedReview = Optional.of(new Review(1, LocalDate.of(2018, 8, 22)
                , "Curabitur convallis.", 1, 1));
        Optional<Review> actualUser = reviewDAO.getById(1);
        assertEquals(expectedReview, actualUser);
    }

    @Test
    public void getByWrongId() {
        Optional<Review> reviewActual = reviewDAO.getById(-1);
        assertEquals(Optional.empty(), reviewActual);
    }

    @Test
    public void delete() {
        int actual = reviewDAO.delete(1);
        assertEquals(1, actual);
    }

    @Test
    public void deleteForWrongId() {
        int actual = reviewDAO.delete(-1);
        assertEquals(0, actual);
    }

    @Test
    public void create() {
        int actualResult = reviewDAO.create(new Review(1, LocalDate.of(2018, 8, 22)
                , "Curabitur convallis.", 1, 1));
        assertEquals(1, actualResult);
    }

    @Test
    public void update() {
        expectedReview = Optional.of(new Review(1, LocalDate.of(2018, 8, 22), "Curabitur convallis.", 1, 1));
        Optional<Review> reviewActual = reviewDAO.update(new Review(10, LocalDate.of(2018, 8, 22), "Curabitur convallis.", 1, 1), 1);
        assertEquals(expectedReview, reviewActual);
    }

    @Test
    public void updateForWrongId() {
        Optional<Review> reviewActual = reviewDAO.update(new Review(10, LocalDate.of(2018, 8
                , 22), "Curabitur convallis.", 1, 1), -1);
        assertEquals(Optional.empty(), reviewActual);
    }

}