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

import static by.epam.kunitski.model.entity.Hotel.FeatureType.CHILDREN_AREA;
import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelDAOImplTest {

    private Hotel expectedHotel;

    @Autowired
    private HotelDAOImpl hotelDAO;

    @Autowired
    Flyway flyway;

    @Before
    public void init(){
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
        expectedHotel = new Hotel(1, "Choloepus hoffmani", 2, "kvassman0@wikimedia.org"
                ,8.2673715, 48.9086571, CHILDREN_AREA);
        Hotel actualUser = hotelDAO.getById(1);
        assertEquals(expectedHotel, actualUser);
    }

    @Test
    public void getByWrongId() {
        Hotel actualUser = hotelDAO.getById(-1);
        assertEquals(null, actualUser);
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
        boolean actualResult = hotelDAO.create(new Hotel(1, "Choloepus hoffmani", 2
                , "kvassman0@wikimedia.org", 8.2673715, 48.9086571, CHILDREN_AREA));
        assertEquals(true, actualResult);
    }

    @Test
    public void createHotelNull() {
        boolean actualResult = hotelDAO.create(null);
        assertEquals(false, actualResult);
    }

    @Test
    public void update() {
        expectedHotel = new Hotel(1, "Reverance", 2, "kvassman0@wikimedia.org", 8.2673715, 48.9086571, CHILDREN_AREA);
        Hotel hotelActual = hotelDAO.update(new Hotel(10, "Reverance", 2, "kvassman0@wikimedia.org", 8.2673715, 48.9086571, CHILDREN_AREA), 1);
        assertEquals(expectedHotel, hotelActual);
    }

    @Test
    public void updateForWrongId() {
        Hotel hotelActual = hotelDAO.update(new Hotel(10, "Reverance", 2, "kvassman0@wikimedia.org", 8.2673715, 48.9086571, CHILDREN_AREA), -1);
        assertEquals(null, hotelActual);
    }

    @Test
    public void updateForHotelNull() {
        Hotel hotelActual = hotelDAO.update(null, 1);
        assertEquals(null, hotelActual);
    }
}