package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.UserDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.impl.UserSpecification;
import by.epam.kunitski.travelagency.dao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private User expectedUser = new User();

    @Mock
    private Validator validator;

    @Mock
    Set<ConstraintViolation<User>> expViolations;

    @Mock
    private UserDAOImpl userDAO;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void findAll() {
        UserSpecification userSpecification = new UserSpecification();

        when(userDAO.getAllByCriteria(userSpecification)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), userServiceImpl.findAllByCriteria(userSpecification));
    }

    @Test
    public void findById() {
        when(userDAO.getById(1)).thenReturn(Optional.of(expectedUser));
        assertEquals(Optional.of(expectedUser), userServiceImpl.findById(1));
    }

    @Test
    public void findByUserName() {
        expectedUser.setLogin("Admin");
        when(userDAO.findUserByUsername("Admin")).thenReturn(expectedUser);
        assertEquals(expectedUser, userServiceImpl.findByUserName("Admin"));
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
    public void addValid() {
        when(validator.validate(expectedUser)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(true);
        when(userDAO.create(expectedUser)).thenReturn(expectedUser);

        userServiceImpl.add(expectedUser);

        verify(userDAO, times(1)).create(expectedUser);
    }

    @Test
    public void addNotValid() {
        when(validator.validate(expectedUser)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(false);

        userServiceImpl.add(expectedUser);

        verify(userDAO, times(0)).create(expectedUser);
    }

    @Test
    public void addByNull() {
        userServiceImpl.add(null);
    }

    @Test
    public void updateValid() {
        when(validator.validate(expectedUser)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(true);
        when(userDAO.update(expectedUser)).thenReturn(expectedUser);

        userServiceImpl.update(expectedUser);

        verify(userDAO, times(1)).update(expectedUser);
    }

    @Test
    public void updateNotValid() {
        when(validator.validate(expectedUser)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(false);

        userServiceImpl.update(expectedUser);

        verify(userDAO, times(0)).update(expectedUser);
    }

    @Test
    public void updateByNull() {
        userServiceImpl.update(null);
    }
}