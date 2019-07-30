package by.epam.kunitski.travelagency.service.impl;

import static by.epam.kunitski.travelagency.entity.Tour.TourType.ONLY_BREAKFAST;
import static org.junit.Assert.*;

import by.epam.kunitski.travelagency.dao.impl.TourDAOImpl;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.exception.EntityNullValueRuntimeException;
import by.epam.kunitski.travelagency.service.TourService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TourServiceImplTest {

    private Tour expectedTour = new Tour(); //(1, "http://dummyimage.com/147x238.jpg/cc0000/ffffff"
            //, LocalDate.of(2019, 1, 15), 3
//            , "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.", 231.70
//            , 1, 1, ONLY_BREAKFAST);

    @Mock
    private TourDAOImpl tourDAO;

    @InjectMocks
    private TourServiceImpl tourServiceImpl;

    @Test
    public void findAll() {
        when(tourDAO.getAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), tourServiceImpl.findAll());
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
//        when(tourDAO.getById(1)).thenReturn(Optional.of(expectedTour));
        assertTrue(tourServiceImpl.delete(1));
    }

    @Test
    public void deleteFail() {
//        when(tourDAO.getById(1)).thenReturn(Optional.of(expectedTour));
//        when(tourDAO.delete(1)).thenReturn(0);
        assertFalse(tourServiceImpl.delete(1));
    }

    @Test
    public void deleteByWrongId() {
//        when(tourDAO.getById(-1)).thenReturn(Optional.empty());
        assertFalse(tourServiceImpl.delete(-1));
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
        assertEquals(expectedTour, tourServiceImpl.update(expectedTour, 1));
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void updateByNull() {
        tourServiceImpl.update(null, 1);
    }

}