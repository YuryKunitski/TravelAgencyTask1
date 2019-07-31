package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.CountryDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.dao.specification.impl.CountrySpecification;
import by.epam.kunitski.travelagency.entity.Country;
import by.epam.kunitski.travelagency.exception.EntityNullValueRuntimeException;
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
public class CountryServiceImplTest {

    private Country expectedCountry = new Country();

    @Mock
    private CountryDAOImpl countryDAO;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    public void findAll() {
        CountrySpecification countrySpecification = new CountrySpecification();

        when(countryDAO.getAll(countrySpecification)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), countryService.findAll(countrySpecification));
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
//        when(countryDAO.delete(1)).thenReturn(1);
//        when(countryDAO.getById(1)).thenReturn(Optional.of(expectedCountry));
//        assertTrue(countryService.delete(1));
    }

    @Test
    public void deleteFail() {
//        when(countryDAO.getById(1)).thenReturn(Optional.of(expectedCountry));
//        when(countryDAO.delete(1)).thenReturn(0);
//        assertFalse(countryService.delete(1));
    }

    @Test
    public void deleteByWrongId() {
//        when(countryDAO.getById(-1)).thenReturn(Optional.empty());
        assertFalse(countryService.delete(-1));
    }

    @Test
    public void add() {
        expectedCountry.setId(26);
        when(countryDAO.create(expectedCountry)).thenReturn(expectedCountry);
        Country actualCountry = countryService.add(expectedCountry);
        assertEquals(expectedCountry, actualCountry);
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void addByNull() {
        countryService.add(null);
    }

    @Test
    public void update() {
//        when(countryDAO.update(expectedCountry, 1)).thenReturn(Optional.of(expectedCountry));
//        assertEquals(expectedCountry, countryService.update(expectedCountry, 1));
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void updateByNull() {
        countryService.update(null, 1);
    }
}