package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.dbconfig.TestConfig;
import by.epam.kunitski.model.entity.Tour;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Optional;

import static by.epam.kunitski.model.entity.Tour.TourType.ONLY_BREAKFAST;
import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TourDAOImplTest {

    private Optional<Tour> expectedTour;

    @Autowired
    private TourDAOImpl tourDAO;

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
        int sizeActual = tourDAO.getAll().size();
        assertEquals(sizeExpected, sizeActual);
    }


    @Test
    public void getById() {
        expectedTour = Optional.of(new Tour(1, "http://dummyimage.com/147x238.jpg/cc0000/ffffff", LocalDate.of(2019, 01, 15)
                , 3, "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.", 231.70, 1, 1, ONLY_BREAKFAST));
        Optional<Tour> actualTour = tourDAO.getById(1);
        assertEquals(expectedTour, actualTour);
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
        int actualResult = tourDAO.create(new Tour(1, "http://dummyimage.com/147x238.jpg/cc0000/ffffff"
                , LocalDate.of(2019, 01, 15), 3, "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst."
                , 231.70, 1, 1, ONLY_BREAKFAST));
        assertEquals(1, actualResult);
    }

//    @Test(expected = NullPointerException.class)
//    public void createTourNull() {
//        int actualResult = tourDAO.create(null);
//        assertEquals(false, actualResult);
//    }

    @Test
    public void update() {
        expectedTour = Optional.of(new Tour(1, "http://dummyimage.com/147x238.jpg/cc0000/ffffff"
                , LocalDate.of(2019, 01, 15), 3, "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst."
                , 231.70, 1, 1, ONLY_BREAKFAST));

        Optional<Tour> tourActual = tourDAO.update(new Tour(100, "http://dummyimage.com/147x238.jpg/cc0000/ffffff"
                , LocalDate.of(2019, 01, 15), 3, "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst."
                , 231.70, 1, 1, ONLY_BREAKFAST), 1);

        assertEquals(expectedTour, tourActual);
    }

    @Test
    public void updateForWrongId() {
        Optional<Tour> tourActual = tourDAO.update(new Tour(100, "http://dummyimage.com/147x238.jpg/cc0000/ffffff"
                , LocalDate.of(2019, 01, 15), 3, "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst."
                , 231.70, 1, 1, ONLY_BREAKFAST), -1);

        assertEquals(Optional.empty(), tourActual);
    }

//    @Test(expected = NullPointerException.class)
//    public void updateForTourNull() {
//        Tour tourActual = tourDAO.update(null, -1);
//
//        assertEquals(null, tourActual);
//    }
}