package by.epam.kunitski.model.service.impl;

import static by.epam.kunitski.model.entity.Tour.TourType.ONLY_BREAKFAST;
import static org.junit.Assert.*;
import by.epam.kunitski.model.dao.daoimpl.TourDAOImpl;
import by.epam.kunitski.model.entity.Tour;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TourServiceTest {

    private Tour expectedTour = new Tour(1, "http://dummyimage.com/147x238.jpg/cc0000/ffffff"
            , LocalDate.of(2019, 1, 15), 3
            , "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.", 231.70
            , 1, 1, ONLY_BREAKFAST);

    @Mock
    private TourDAOImpl tourDAO;

    @InjectMocks
    private TourService tourService;

    @Test
    public void findAll() {
        when(tourDAO.getAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), tourService.findAll());

    }

    @Test
    public void findById() {

        when(tourDAO.getById(1)).thenReturn(Optional.of(expectedTour));
        assertEquals(Optional.of(expectedTour), tourService.findById(1));
    }

    @Test
    public void findByWrongId() {
        when(tourDAO.getById(-1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), tourService.findById(-1));
    }

    @Test
    public void delete() {
        when(tourDAO.delete(1)).thenReturn(1);
        when(tourDAO.getById(1)).thenReturn(Optional.of(expectedTour));
        assertTrue(tourService.delete(1));
    }

    @Test
    public void deleteFail() {
        when(tourDAO.getById(1)).thenReturn(Optional.of(expectedTour));
        when(tourDAO.delete(1)).thenReturn(0);
        assertFalse(tourService.delete(1));
    }

    @Test
    public void deleteByWrongId() {
        when(tourDAO.getById(-1)).thenReturn(Optional.empty());
        assertFalse(tourService.delete(-1));
    }

    @Test
    public void add() {
        lenient().when(tourDAO.getById(30)).thenReturn(Optional.empty());
        when(tourDAO.create(expectedTour)).thenReturn(1);
        assertTrue(tourService.add(expectedTour));
    }

//    @Test
//    public void addFail() {
//        lenient().when(tourDAO.getById(30)).thenReturn(Optional.empty());
//        when(tourDAO.create(expectedTour)).thenReturn(0);
//        assertFalse(tourService.add(expectedTour));
//    }

    @Test
    public void addByExistWrongId() {
        when(tourDAO.getById(1)).thenReturn(Optional.of(expectedTour));
        lenient().when(tourDAO.create(expectedTour)).thenReturn(0);
        assertFalse(tourService.add(expectedTour));
    }

    @Test
    public void addByNull() {
        assertFalse(tourService.add(null));
    }

    @Test
    public void update() {
        when(tourDAO.update(expectedTour, 1)).thenReturn(Optional.of(expectedTour));
        assertEquals(Optional.of(expectedTour), tourService.update(expectedTour, 1));
    }

    @Test
    public void updateWrongId() {
        when(tourDAO.update(expectedTour, -1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), tourService.update(expectedTour, -1));
    }

    @Test
    public void updateByNull() {
        assertEquals(Optional.empty(), tourService.update(null, 1));
    }

}