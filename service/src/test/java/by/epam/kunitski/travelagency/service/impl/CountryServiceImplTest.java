package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.CountryDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.impl.CountrySpecification;
import by.epam.kunitski.travelagency.dao.entity.Country;
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
public class CountryServiceImplTest {

    private Country expectedCountry = new Country();

    @Mock
    private CountryDAOImpl countryDAO;

    @Mock
    private Validator validator;

    @Mock
    Set<ConstraintViolation<Country>> expViolations;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    public void findAll() {
        CountrySpecification countrySpecification = new CountrySpecification();

        when(countryDAO.getAllByCriteria(countrySpecification)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), countryService.findAllByCriteria(countrySpecification));
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
        when(countryDAO.delete(1)).thenReturn(true);
        assertTrue(countryService.delete(1));
    }

    @Test
    public void deleteFail() {
        when(countryDAO.delete(1)).thenReturn(false);
        assertFalse(countryService.delete(1));
    }

    @Test
    public void addValid() {
        when(validator.validate(expectedCountry)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(true);
        when(countryDAO.create(expectedCountry)).thenReturn(expectedCountry);

        countryService.add(expectedCountry);

        verify(countryDAO, times(1)).create(expectedCountry);
    }

    @Test
    public void addNotValid() {

        when(validator.validate(expectedCountry)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(false);

        countryService.add(expectedCountry);

        verify(countryDAO, times(0)).create(expectedCountry);
    }

    @Test
    public void addByNull() {
        assertTrue(countryService.add(null).isEmpty());
    }

    @Test
    public void updateValid() {
        when(validator.validate(expectedCountry)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(true);
        when(countryDAO.update(expectedCountry)).thenReturn(expectedCountry);

        countryService.update(expectedCountry);

        verify(countryDAO, times(1)).update(expectedCountry);
    }

    @Test
    public void updateNotValid() {
        when(validator.validate(expectedCountry)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(false);

        countryService.update(expectedCountry);


        verify(countryDAO, times(0)).update(expectedCountry);
    }

    @Test
    public void updateByNull() {
        countryService.update(null);
    }
}