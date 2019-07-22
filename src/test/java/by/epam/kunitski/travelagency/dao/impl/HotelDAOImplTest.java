package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.config.TestConfig;
import by.epam.kunitski.travelagency.entity.Hotel;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static by.epam.kunitski.travelagency.entity.Hotel.FeatureType.CHILDREN_AREA;
import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelDAOImplTest {

    private Hotel expHotel = new Hotel(1, "Choloepus hoffmani", 2, "kvassman0@wikimedia.org"
            , 8.2673715, 48.9086571, CHILDREN_AREA);

    @Autowired
    private HotelDAOImpl hotelDAO;

    @Autowired
    private Flyway flyway;

    @Before
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void getAll() {
        int sizeExpected = 100;
        int sizeActual = hotelDAO.getAll().size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getById() {
        Hotel actualHotel = hotelDAO.getById(1).get();
        assertEquals(expHotel, actualHotel);
    }

    @Test
    public void getByWrongId() {
        Optional<Hotel> actualHotel = hotelDAO.getById(-1);
        assertEquals(Optional.empty(), actualHotel);
    }

    @Test
    public void delete() {
        int actual = hotelDAO.delete(100);
        assertEquals(1, actual);
    }

    @Test
    public void deleteForWrongId() {
        int actual = hotelDAO.delete(-1);
        assertEquals(0, actual);
    }

    @Test
    public void create() {
        Hotel actualHotel = hotelDAO.create(expHotel);
        assertEquals(expHotel, actualHotel);
    }

    @Test
    public void update() {
//        expectedHotel = Optional.of(expHotel);
        Hotel hotelActual = hotelDAO.update(expHotel, 1).get();
        assertEquals(expHotel, hotelActual);
    }

}