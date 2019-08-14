package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.HotelDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.impl.HotelSpecification;
import by.epam.kunitski.travelagency.entity.Hotel;
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
public class HotelServiceImplTest {

    private Hotel expectedHotel = new Hotel();

    @Mock
    private Validator validator;

    @Mock
    Set<ConstraintViolation<Hotel>> expViolations;

    @Mock
    private HotelDAOImpl hotelDAO;

    @InjectMocks
    private HotelServiceImpl hotelServiceImpl;

    @Test
    public void findAll() {
        HotelSpecification hotelSpecification = new HotelSpecification();

        when(hotelDAO.getAllByCriteria(hotelSpecification)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), hotelServiceImpl.findAllByCriteria(hotelSpecification));

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
    public void addValid() {
        when(validator.validate(expectedHotel)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(true);
        when(hotelDAO.create(expectedHotel)).thenReturn(expectedHotel);

        hotelServiceImpl.add(expectedHotel);

        verify(hotelDAO, times(1)).create(expectedHotel);
    }

    @Test
    public void addNotValid() {
        when(validator.validate(expectedHotel)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(false);

        hotelServiceImpl.add(expectedHotel);

        verify(hotelDAO, times(0)).create(expectedHotel);
    }

    @Test
    public void addByNull() {
        hotelServiceImpl.add(null);
    }

    @Test
    public void updateValid() {
        when(validator.validate(expectedHotel)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(true);
        when(hotelDAO.update(expectedHotel)).thenReturn(expectedHotel);

        hotelServiceImpl.update(expectedHotel);

        verify(hotelDAO, times(1)).update(expectedHotel);
    }

    @Test
    public void updateNotValid() {
        when(validator.validate(expectedHotel)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(false);

        hotelServiceImpl.update(expectedHotel);

        verify(hotelDAO, times(0)).update(expectedHotel);
    }

    @Test
    public void updateByNull() {
        hotelServiceImpl.update(null);
    }
}