package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.dbconfig.TestConfig;
import by.epam.kunitski.model.entity.Review;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewDAOImplTest {

    private Review expectedReview;

    @Autowired
    private ReviewDAOImpl reviewDAO;

    @Autowired
    Flyway flyway;

    @Before
    public void init(){
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
        expectedReview = new Review(1, LocalDate.of(2018, 8, 22), "Curabitur convallis.", 1, 1);
        Review actualUser = reviewDAO.getById(1);
        assertEquals(expectedReview, actualUser);
    }

    @Test
    public void getByWrongId() {
        Review actualUser = reviewDAO.getById(-1);
        assertEquals(null, actualUser);
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
        boolean actualResult = reviewDAO.create(new Review(1, LocalDate.of(2018, 8, 22), "Curabitur convallis.", 1, 1));
        assertEquals(true, actualResult);
    }

    @Test
    public void createUserNull() {
        boolean actualResult = reviewDAO.create(null);
        assertEquals(false, actualResult);
    }

    @Test
    public void update() {
        expectedReview = new Review(1, LocalDate.of(2018, 8, 22), "Curabitur convallis.", 1, 1);
        Review reviewActual = reviewDAO.update(new Review(10, LocalDate.of(2018, 8, 22), "Curabitur convallis.", 1, 1), 1);
        assertEquals(expectedReview, reviewActual);
    }

    @Test
    public void updateForWrongId() {
        Review reviewActual = reviewDAO.update(new Review(10, LocalDate.of(2018, 8, 22), "Curabitur convallis.", 1, 1), -1);
        assertEquals(null, reviewActual);
    }

    @Test
    public void updateForReviewNull() {
        Review reviewActual = reviewDAO.update(null, 1);
        assertEquals(null, reviewActual);
    }
}