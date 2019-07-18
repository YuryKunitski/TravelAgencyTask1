package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.dbconfig.TestConfig;
import by.epam.kunitski.model.entity.Hotel;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static by.epam.kunitski.model.entity.Hotel.FeatureType.CHILDREN_AREA;
import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelDAOImplTest {

    private Optional<Hotel> expectedHotel;

    @Autowired
    private HotelDAOImpl hotelDAO;

    @Autowired
    Flyway flyway;

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
        expectedHotel = Optional.of(new Hotel(1, "Choloepus hoffmani", 2, "kvassman0@wikimedia.org"
                , 8.2673715, 48.9086571, CHILDREN_AREA));
        Optional<Hotel> actualUser = hotelDAO.getById(1);
        assertEquals(expectedHotel, actualUser);
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
        int actualResult = hotelDAO.create(new Hotel(1, "Choloepus hoffmani", 2
                , "kvassman0@wikimedia.org", 8.2673715, 48.9086571, CHILDREN_AREA));
        assertEquals(1, actualResult);
    }

    @Test
    public void update() {
        expectedHotel = Optional.of(new Hotel(1, "Reverance", 2, "kvassman0@wikimedia.org"
                , 8.2673715, 48.9086571, CHILDREN_AREA));
        Optional<Hotel> hotelActual = hotelDAO.update(new Hotel(10, "Reverance", 2
                , "kvassman0@wikimedia.org", 8.2673715, 48.9086571, CHILDREN_AREA), 1);
        assertEquals(expectedHotel, hotelActual);
    }

    @Test
    public void updateForWrongId() {
        Optional<Hotel> hotelActual = hotelDAO.update(new Hotel(10, "Reverance", 2
                , "kvassman0@wikimedia.org", 8.2673715, 48.9086571, CHILDREN_AREA), -1);
        assertEquals(Optional.empty(), hotelActual);
    }

}