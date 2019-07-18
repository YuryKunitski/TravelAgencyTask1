package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.HotelDAOImpl;
import by.epam.kunitski.travelagency.entity.Hotel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static by.epam.kunitski.travelagency.entity.Hotel.FeatureType.CHILDREN_AREA;
import static org.junit.Assert.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceTest {

    private Hotel expectedHotel = new Hotel(1, "Choloepus hoffmani", 2
            , "kvassman0@wikimedia.org", 8.2673715, 48.9086571, CHILDREN_AREA);

    @Mock
    private HotelDAOImpl hotelDAO;

    @InjectMocks
    private HotelService hotelService;

    @Test
    public void findAll() {
        when(hotelDAO.getAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), hotelService.findAll());

    }

    @Test
    public void findById() {

        when(hotelDAO.getById(1)).thenReturn(Optional.of(expectedHotel));
        assertEquals(Optional.of(expectedHotel), hotelService.findById(1));
    }

    @Test
    public void findByWrongId() {
        when(hotelDAO.getById(-1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), hotelService.findById(-1));
    }

    @Test
    public void delete() {

        when(hotelDAO.delete(1)).thenReturn(1);
        when(hotelDAO.getById(1)).thenReturn(Optional.of(expectedHotel));
        assertTrue(hotelService.delete(1));
    }

    @Test
    public void deleteFail() {
        when(hotelDAO.getById(1)).thenReturn(Optional.of(expectedHotel));
        when(hotelDAO.delete(1)).thenReturn(0);
        assertFalse(hotelService.delete(1));
    }

    @Test
    public void deleteByWrongId() {
        when(hotelDAO.getById(-1)).thenReturn(Optional.empty());
        assertFalse(hotelService.delete(-1));
    }

    @Test
    public void add() {
        lenient().when(hotelDAO.getById(30)).thenReturn(Optional.empty());
        when(hotelDAO.create(expectedHotel)).thenReturn(1);
        assertTrue(hotelService.add(expectedHotel));
    }

    @Test
    public void addFail() {
        lenient().when(hotelDAO.getById(30)).thenReturn(Optional.empty());
        when(hotelDAO.create(expectedHotel)).thenReturn(0);
        assertFalse(hotelService.add(expectedHotel));
    }

    @Test
    public void addByExistWrongId() {
        when(hotelDAO.getById(1)).thenReturn(Optional.of(expectedHotel));
        lenient().when(hotelDAO.create(expectedHotel)).thenReturn(0);
        assertFalse(hotelService.add(expectedHotel));
    }

    @Test
    public void addByNull() {
        assertFalse(hotelService.add(null));
    }

    @Test
    public void update() {
        when(hotelDAO.update(expectedHotel, 1)).thenReturn(Optional.of(expectedHotel));
        assertEquals(Optional.of(expectedHotel), hotelService.update(expectedHotel, 1));
    }

    @Test
    public void updateWrongId() {
        when(hotelDAO.update(expectedHotel, -1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), hotelService.update(expectedHotel, -1));
    }

    @Test
    public void updateByNull() {
        assertEquals(Optional.empty(), hotelService.update(null, 1));
    }
}