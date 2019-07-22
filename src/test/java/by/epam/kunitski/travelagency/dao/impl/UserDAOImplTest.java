package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.config.TestConfig;
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

    private User expUser = new User(1, "Saundra", "CDHjDf5Tnr");

    @Autowired
    private UserDAOImpl userDAO;

    @Autowired
    private
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
        User actualUser = userDAO.getById(1).get();
        assertEquals(expUser, actualUser);
    }

    @Test
    public void getByWrongId() {
        Optional<User> actualUser = userDAO.getById(-1);
        assertEquals(Optional.empty(), actualUser);
    }

    @Test
    public void create() {
        User actualUser = userDAO.create(expUser);
        assertEquals(expUser, actualUser);
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
        User userActual = userDAO.update(expUser, 1).get();
        assertEquals(expUser, userActual);
    }
//
//    @Test
//    public void updateForWrongId() {
//        Optional<User> userActual = userDAO.update(new User(1, "Lisa", "eee"), -1);
//        assertEquals(Optional.empty(), userActual);
//    }

}