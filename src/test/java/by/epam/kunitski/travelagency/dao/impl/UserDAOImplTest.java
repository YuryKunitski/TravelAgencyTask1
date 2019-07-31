package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.EntityDAO;
import by.epam.kunitski.travelagency.dao.config.TestConfig;
import by.epam.kunitski.travelagency.entity.User;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDAOImplTest {

    private User expUser = new User();

    @Autowired
    @Qualifier("userDAOImpl")
    private EntityDAO<User> userDAO;

    @Autowired
    private Flyway flyway;

    @Before
    public void init() {

        expUser = InitEntity.initUser();

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
        expUser.setLogin("Saundra");
        User actualUser = userDAO.getById(1).get();
        assertEquals(expUser.getLogin(), actualUser.getLogin());
    }

    @Test
    public void getByWrongId() {
        Optional<User> actualUser = userDAO.getById(-1);
        assertEquals(Optional.empty(), actualUser);
    }

    @Test
    public void create() {
        User actualUser = userDAO.create(expUser);
        int generatedId = 101;
        assertEquals(generatedId, actualUser.getId());
    }

    @Test
    public void delete() {
       assertTrue(userDAO.delete(100));
    }

    @Test
    public void deleteForWrongId() {
       assertFalse(userDAO.delete(-1));
    }

    @Test
    public void update() {
        expUser.setId(10);
        User userActual = userDAO.update(expUser);
        assertEquals(expUser, userActual);
    }

}