package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.dbconfig.TestConfig;
import by.epam.kunitski.travelagency.entity.User;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDAOImplTest {

    private Optional<User> expectedUser;

    @Autowired
    private UserDAOImpl userDAO;

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
        int sizeActual = userDAO.getAll().size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getById() {
        expectedUser = Optional.of(new User(2, "Flor", "T39TOUmA4"));
        Optional<User> actualUser = userDAO.getById(2);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getByWrongId() {
        Optional<User> actualUser = userDAO.getById(-1);
        assertEquals(Optional.empty(), actualUser);
    }

    @Test
    public void create() {
        int actualResult = userDAO.create(new User(1, "Smit", "a5C4mtyg"));
        assertEquals(1, actualResult);
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
        expectedUser = Optional.of(new User(1, "Jonny", "a5C4mtyg"));
        Optional<User> userActual = userDAO.update(new User(1, "Jonny", "a5C4mtyg"), 1);
        assertEquals(expectedUser, userActual);
    }

    @Test
    public void updateForWrongId() {
        Optional<User> userActual = userDAO.update(new User(1, "Lisa", "eee"), -1);
        assertEquals(Optional.empty(), userActual);
    }

}