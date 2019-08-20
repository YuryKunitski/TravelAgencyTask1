package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.UserDAO;
import by.epam.kunitski.travelagency.dao.config.DaoConfig;
import by.epam.kunitski.travelagency.dao.specification.impl.UserSpecification;
import by.epam.kunitski.travelagency.dao.entity.User;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = DaoConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class UserDAOImplTest {

    private User expUser = new User();

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Flyway flyway;

    @Before
    public void init() {

        expUser = InitEntity.initUser();

        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void getByUserName() {
        expUser.setLogin("Saundra");
        User actualUser = userDAO.findUserByUsername("Saundra");

        assertEquals(expUser.getLogin(), actualUser.getLogin());
    }

    @Test
    public void getAll() {
        int sizeExpected = 100;
        int sizeActual = userDAO.getAll().size();
        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getAllByCriteria() {
        UserSpecification userSpecification = new UserSpecification();

        int sizeExpected = 100;
        int sizeActual = userDAO.getAllByCriteria(userSpecification).size();
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

    @Transactional
    @Test
    public void create() {
        User actualUser = userDAO.create(expUser);
        int generatedId = 101;
        assertEquals(generatedId, actualUser.getId());
    }

    @Transactional
    @Test
    public void delete() {
        assertTrue(userDAO.delete(100));
    }

    @Transactional
    @Test
    public void deleteForWrongId() {
        assertFalse(userDAO.delete(-1));
    }

    @Transactional
    @Test
    public void update() {
        expUser.setId(10);
        User userActual = userDAO.update(expUser);
        assertEquals(expUser, userActual);
    }

}