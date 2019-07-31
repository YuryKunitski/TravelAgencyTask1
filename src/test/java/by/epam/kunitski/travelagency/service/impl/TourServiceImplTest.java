package by.epam.kunitski.travelagency.service.impl;

import static org.junit.Assert.*;

import by.epam.kunitski.travelagency.dao.impl.TourDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.entity.Tour;
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
public class TourServiceImplTest {

    private Tour expectedTour = new Tour();

    @Mock
    private TourDAOImpl tourDAO;

    @InjectMocks
    private TourServiceImpl tourServiceImpl;

    @Test
    public void findAll() {
        TourSpecification tourSpecification = new TourSpecification();

        when(tourDAO.getAll(tourSpecification)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), tourServiceImpl.findAll(tourSpecification));
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
    public void add() {
        when(tourDAO.create(expectedTour)).thenReturn(expectedTour);
        Tour actualTour = tourServiceImpl.add(expectedTour);
        expectedTour.setId(1001);
        assertEquals(expectedTour, actualTour);
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void addByNull() {
        tourServiceImpl.add(null);
    }

    @Test
    public void update() {
        when(tourDAO.update(expectedTour)).thenReturn(expectedTour);
        assertEquals(expectedTour, tourServiceImpl.update(expectedTour));
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void updateByNull() {
        tourServiceImpl.update(null);
    }

}