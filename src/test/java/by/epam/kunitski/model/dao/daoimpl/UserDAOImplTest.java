package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.dbconfig.TestConfig;
import by.epam.kunitski.model.entity.User;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDAOImplTest {

    private User expectedUser;

    @Autowired
    private UserDAOImpl userDAO;

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
        int sizeActual = userDAO.getAll().size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getById() {
        expectedUser = new User(2, "Flor", "T39TOUmA4");
        User actualUser = userDAO.getById(2);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getByWrongId() {
        User actualUser = userDAO.getById(-1);
        assertEquals(null, actualUser);
    }

    @Test
    public void create() {
        boolean actualResult = userDAO.create(new User(1, "Smit", "a5C4mtyg"));
        assertEquals(true, actualResult);
    }

    @Test
    public void createUserNull() {
        boolean actualResult = userDAO.create(null);
        assertEquals(false, actualResult);
    }

    @Test
    public void delete() {
        int actual = userDAO.delete(100);
        assertEquals(1, actual);

    }

    @Test
    public void deleteForWrongId() {
        int actual = userDAO.delete(-1);
        assertEquals(0, actual);

    }

    @Test
    public void update() {
        expectedUser = new User(1, "Jonny", "a5C4mtyg");
        User userActual = userDAO.update(new User(1, "Jonny", "a5C4mtyg"), 1);
        assertEquals(expectedUser, userActual);
    }

    @Test
    public void updateForWrongId() {
        User userActual = userDAO.update(new User(1, "Lisa", "eee"), -1);
        assertEquals(null, userActual);
    }

    @Test
    public void updateForUserNull() {
        User userActual = userDAO.update(null, 1);
        assertEquals(null, userActual);
    }
}