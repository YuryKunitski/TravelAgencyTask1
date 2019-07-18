package by.epam.kunitski.model.service.impl;

import by.epam.kunitski.model.dao.daoimpl.ReviewDAOImpl;
import by.epam.kunitski.model.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceTest {

    private Review expectedReview = new Review(1, LocalDate.of(2018, 8, 22)
            , "Curabitur convallis.", 1, 1);

    @Mock
    private ReviewDAOImpl reviewDAO;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    public void findAll() {
        when(reviewDAO.getAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), reviewService.findAll());

    }

    @Test
    public void findById() {
        when(reviewDAO.getById(1)).thenReturn(Optional.of(expectedReview));
        assertEquals(Optional.of(expectedReview), reviewService.findById(1));
    }

    @Test
    public void findByWrongId() {
        when(reviewDAO.getById(-1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), reviewService.findById(-1));
    }

    @Test
    public void delete() {
        when(reviewDAO.delete(1)).thenReturn(1);
        when(reviewDAO.getById(1)).thenReturn(Optional.of(expectedReview));
        assertTrue(reviewService.delete(1));
    }
    
    @Test
    public void deleteFail() {
        when(reviewDAO.getById(1)).thenReturn(Optional.of(expectedReview));
        when(reviewDAO.delete(1)).thenReturn(0);
        assertFalse(reviewService.delete(1));
    }

    @Test
    public void deleteByWrongId() {
        when(reviewDAO.getById(-1)).thenReturn(Optional.empty());
        assertFalse(reviewService.delete(-1));
    }

    @Test
    public void add() {
        lenient().when(reviewDAO.getById(30)).thenReturn(Optional.empty());
        when(reviewDAO.create(expectedReview)).thenReturn(1);
        assertTrue(reviewService.add(expectedReview));
    }

    @Test
    public void addFail() {
        lenient().when(reviewDAO.getById(30)).thenReturn(Optional.empty());
        when(reviewDAO.create(expectedReview)).thenReturn(0);
        assertFalse(reviewService.add(expectedReview));
    }

    @Test
    public void addByExistWrongId() {
        when(reviewDAO.getById(1)).thenReturn(Optional.of(expectedReview));
        lenient().when(reviewDAO.create(expectedReview)).thenReturn(0);
        assertFalse(reviewService.add(expectedReview));
    }

    @Test
    public void addByNull() {
        assertFalse(reviewService.add(null));
    }

    @Test
    public void update() {
        when(reviewDAO.update(expectedReview, 1)).thenReturn(Optional.of(expectedReview));
        assertEquals(Optional.of(expectedReview), reviewService.update(expectedReview, 1));
    }

    @Test
    public void updateWrongId() {
        when(reviewDAO.update(expectedReview, -1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), reviewService.update(expectedReview, -1));
    }

    @Test
    public void updateByNull() {
        assertEquals(Optional.empty(), reviewService.update(null, 1));
    }
}
