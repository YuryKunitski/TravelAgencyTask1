package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.HotelDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.impl.HotelSpecification;
import by.epam.kunitski.travelagency.entity.Hotel;
import by.epam.kunitski.travelagency.exception.EntityNullValueRuntimeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceImplTest {

    private Hotel expectedHotel = new Hotel();

    @Mock
    private HotelDAOImpl hotelDAO;

    @InjectMocks
    private HotelServiceImpl hotelServiceImpl;

    @Test
    public void findAll() {
        HotelSpecification hotelSpecification = new HotelSpecification();

        when(hotelDAO.getAll(hotelSpecification)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), hotelServiceImpl.findAll(hotelSpecification));

    }

    @Test
    public void findById() {

        when(hotelDAO.getById(1)).thenReturn(Optional.of(expectedHotel));
        assertEquals(Optional.of(expectedHotel), hotelServiceImpl.findById(1));
    }

    @Test
    public void findByWrongId() {
        when(hotelDAO.getById(-1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), hotelServiceImpl.findById(-1));
    }

    @Test
    public void delete() {
        when(hotelDAO.delete(1)).thenReturn(true);
        assertTrue(hotelServiceImpl.delete(1));
    }

    @Test
    public void deleteFail() {
        when(hotelDAO.delete(1)).thenReturn(false);
        assertFalse(hotelServiceImpl.delete(1));
    }

    @Test
    public void add() {
        expectedHotel.setId(100);
        when(hotelDAO.create(expectedHotel)).thenReturn(expectedHotel);
        expectedHotel.setId(0);
        Hotel actualHotel = hotelServiceImpl.add(expectedHotel);
        assertEquals(expectedHotel, actualHotel);
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void addByNull() {
        hotelServiceImpl.add(null);
    }

    @Test
    public void update() {
        when(hotelDAO.update(expectedHotel)).thenReturn(expectedHotel);
        Hotel actualHotel = hotelServiceImpl.update(expectedHotel);
        expectedHotel.setId(10);
        assertEquals(expectedHotel, actualHotel);
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void updateByNull() {
        hotelServiceImpl.update(null);
    }
}