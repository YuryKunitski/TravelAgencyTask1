package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.config.JpaTestConfig;
import by.epam.kunitski.travelagency.dao.HotelDAO;
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

@ContextConfiguration(classes = JpaTestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelDAOImplTest {

    private Hotel expHotel = new Hotel();

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private Flyway flyway;

    @Before
    public void init() {

        expHotel.setName("Turist");

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

        expHotel.setName("Choloepus hoffmani");

        Hotel actualHotel = hotelDAO.getById(1).get();
        assertEquals(expHotel.getName(), actualHotel.getName());
    }

    @Test
    public void getByWrongId() {
        Optional<Hotel> actualHotel = hotelDAO.getById(-1);
        assertEquals(Optional.empty(), actualHotel);
    }

    @Test
    public void delete() {
        assertTrue(hotelDAO.delete(100));
    }

    @Test
    public void deleteForWrongId() {
       assertFalse(hotelDAO.delete(-1));
    }

    @Test
    public void create() {
        Hotel actualHotel = hotelDAO.create(expHotel);
        int generatedId = 101;
        assertEquals(generatedId, actualHotel.getId());
    }

    @Test
    public void update() {
        expHotel.setId(10);
        Hotel hotelActual = hotelDAO.update(expHotel);
        assertEquals(expHotel, hotelActual);
    }

}