package by.epam.kunitski.model.service.impl;

import static org.junit.Assert.*;
import by.epam.kunitski.model.dao.daoimpl.UserDAOImpl;
import by.epam.kunitski.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private User expectedUser = new User(1, "Saundra", "CDHjDf5Tnr");

    @Mock
    private UserDAOImpl userDAO;

    @InjectMocks
    private UserService userService;

    @Test
    public void findAll() {
        when(userDAO.getAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), userService.findAll());

    }

    @Test
    public void findById() {
        when(userDAO.getById(1)).thenReturn(Optional.of(expectedUser));
        assertEquals(Optional.of(expectedUser), userService.findById(1));
    }

    @Test
    public void findByWrongId() {
        when(userDAO.getById(-1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), userService.findById(-1));
    }

    @Test
    public void delete() {
        when(userDAO.delete(1)).thenReturn(1);
        when(userDAO.getById(1)).thenReturn(Optional.of(expectedUser));
        assertTrue(userService.delete(1));
    }

    @Test
    public void deleteByWrongId() {
        when(userDAO.getById(-1)).thenReturn(Optional.empty());
        assertFalse(userService.delete(-1));
    }

    @Test
    public void add() {
        when(userDAO.create(expectedUser)).thenReturn(1);
        assertTrue(userService.add(expectedUser));
    }

    @Test
    public void addByWrongId() {
        lenient().when(userDAO.getById(1)).thenReturn(Optional.of(expectedUser));
        assertFalse(userService.add(expectedUser));
    }

    @Test
    public void addByNull() {
        assertFalse(userService.add(null));
    }

    @Test
    public void update() {
        when(userDAO.update(expectedUser, 1)).thenReturn(Optional.of(expectedUser));
        assertEquals(Optional.of(expectedUser), userService.update(expectedUser, 1));
    }

    @Test
    public void updateWrongId() {
        when(userDAO.update(expectedUser, -1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), userService.update(expectedUser, -1));
    }

    @Test
    public void updateByNull() {
        assertEquals(Optional.empty(), userService.update(null, 1));
    }
}