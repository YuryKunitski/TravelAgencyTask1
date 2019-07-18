package by.epam.kunitski.model.service.impl;

import by.epam.kunitski.model.dao.daoimpl.CountryDAOImpl;
import by.epam.kunitski.model.entity.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {

    private Country expectedCountry = new Country(1, "Belarus");

    @Mock
    private CountryDAOImpl countryDAO;

    @InjectMocks
    private CountryService countryService;

    @Test
    public void findAll() {
        when(countryDAO.getAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), countryService.findAll());
    }

    @Test
    public void findById() {
        when(countryDAO.getById(1)).thenReturn(Optional.of(expectedCountry));
        assertEquals(Optional.of(expectedCountry), countryService.findById(1));
    }

    @Test
    public void findByWrongId() {
        when(countryDAO.getById(-1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), countryService.findById(-1));
    }

    @Test
    public void delete() {
        when(countryDAO.delete(1)).thenReturn(1);
        when(countryDAO.getById(1)).thenReturn(Optional.of(expectedCountry));
        assertTrue(countryService.delete(1));
    }

    @Test
    public void deleteByWrongId() {
        when(countryDAO.getById(-1)).thenReturn(Optional.empty());
        assertFalse(countryService.delete(-1));
    }

    @Test
    public void add() {
        when(countryDAO.create(expectedCountry)).thenReturn(1);
        assertTrue(countryService.add(expectedCountry));
    }

    @Test
    public void addByWrongId() {
        when(countryDAO.getById(-1)).thenReturn(Optional.empty());
        assertFalse(countryService.add(expectedCountry));
    }

    @Test
    public void addByNull() {
        assertFalse(countryService.add(null));
    }

    @Test
    public void update() {
        when(countryDAO.update(expectedCountry, 1)).thenReturn(Optional.of(expectedCountry));
        assertEquals(Optional.of(expectedCountry), countryService.update(expectedCountry, 1));
    }

    @Test
    public void updateWrongId() {
        when(countryDAO.update(expectedCountry, -1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), countryService.update(expectedCountry, -1));
    }

    @Test
    public void updateByNull() {
        assertEquals(Optional.empty(), countryService.update(null, 1));
    }
}