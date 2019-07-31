package by.epam.kunitski.travelagency.service.impl;

import static org.junit.Assert.*;

import by.epam.kunitski.travelagency.dao.impl.UserDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.impl.UserSpecification;
import by.epam.kunitski.travelagency.entity.User;
import by.epam.kunitski.travelagency.exception.EntityNullValueRuntimeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private User expectedUser = new User();

    @Mock
    private UserDAOImpl userDAO;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void findAll() {
        UserSpecification userSpecification = new UserSpecification();

        when(userDAO.getAll(userSpecification)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), userServiceImpl.findAll(userSpecification));
    }

    @Test
    public void findById() {
        when(userDAO.getById(1)).thenReturn(Optional.of(expectedUser));
        assertEquals(Optional.of(expectedUser), userServiceImpl.findById(1));
    }

    @Test
    public void findByWrongId() {
        when(userDAO.getById(-1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), userServiceImpl.findById(-1));
    }

    @Test
    public void delete() {
        when(userDAO.delete(1)).thenReturn(true);
        assertTrue(userServiceImpl.delete(1));
    }

    @Test
    public void deleteFail() {
        when(userDAO.delete(1)).thenReturn(false);
        assertFalse(userServiceImpl.delete(1));
    }

    @Test
    public void add() {
        when(userDAO.create(expectedUser)).thenReturn(expectedUser);
        User actualUser = userServiceImpl.add(expectedUser);
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void addByNull() {
        userServiceImpl.add(null);
    }

    @Test
    public void update() {
        when(userDAO.update(expectedUser)).thenReturn(expectedUser);
        assertEquals(expectedUser, userServiceImpl.update(expectedUser));
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void updateByNull() {
        userServiceImpl.update(null);
    }
}