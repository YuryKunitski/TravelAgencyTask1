package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.TourDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.entity.Tour;
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
public class TourServiceImplTest {

    private Tour expectedTour = new Tour();

    @Mock
    private Validator validator;

    @Mock
    Set<ConstraintViolation<Tour>> expViolations;

    @Mock
    private TourDAOImpl tourDAO;

    @InjectMocks
    private TourServiceImpl tourServiceImpl;

    @Test
    public void findAllByUserId() {
        when(tourDAO.getAllByUserId(1)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), tourServiceImpl.findAllByUserId(1));
    }

    @Test
    public void findAll() {
        TourSpecification tourSpecification = new TourSpecification();

        when(tourDAO.getAllByCriteria(tourSpecification)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), tourServiceImpl.findAllByCriteria(tourSpecification));
    }

    @Test
    public void findById() {

        when(tourDAO.getById(1)).thenReturn(Optional.of(expectedTour));
        assertEquals(Optional.of(expectedTour), tourServiceImpl.findById(1));
    }

    @Test
    public void findByWrongId() {
        when(tourDAO.getById(-1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), tourServiceImpl.findById(-1));
    }

    @Test
    public void delete() {
        when(tourDAO.delete(1)).thenReturn(true);
        assertTrue(tourServiceImpl.delete(1));
    }

    @Test
    public void deleteFail() {
        when(tourDAO.delete(1)).thenReturn(false);
        assertFalse(tourServiceImpl.delete(1));
    }

    @Test
    public void addValid() {
        when(validator.validate(expectedTour)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(true);
        when(tourDAO.create(expectedTour)).thenReturn(expectedTour);

        tourServiceImpl.add(expectedTour);

        verify(tourDAO, times(1)).create(expectedTour);
    }

    @Test
    public void addNotValid() {
        when(validator.validate(expectedTour)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(false);

        tourServiceImpl.add(expectedTour);

        verify(tourDAO, times(0)).create(expectedTour);
    }

    @Test
    public void addByNull() {
        tourServiceImpl.add(null);
    }

    @Test
    public void updateValid() {
        when(validator.validate(expectedTour)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(true);
        when(tourDAO.update(expectedTour)).thenReturn(expectedTour);

        tourServiceImpl.update(expectedTour);

        verify(tourDAO, times(1)).update(expectedTour);
    }

    @Test
    public void updateNotValid() {
        when(validator.validate(expectedTour)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(false);

        tourServiceImpl.update(expectedTour);

        verify(tourDAO, times(0)).update(expectedTour);
    }

    @Test
    public void updateByNull() {
        tourServiceImpl.update(null);
    }

}