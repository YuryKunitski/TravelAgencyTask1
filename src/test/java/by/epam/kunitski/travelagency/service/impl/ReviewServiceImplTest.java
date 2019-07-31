package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.ReviewDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.impl.ReviewSpecification;
import by.epam.kunitski.travelagency.entity.Review;
import by.epam.kunitski.travelagency.exception.EntityNullValueRuntimeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceImplTest {

    private Review expectedReview = new Review(); //1, LocalDate.of(2018, 8, 22)
//            , "Curabitur convallis.", 1, 1);

    @Mock
    private ReviewDAOImpl reviewDAO;

    @InjectMocks
    private ReviewServiceImpl reviewServiceImpl;

    @Test
    public void findAll() {
        ReviewSpecification reviewSpecification = new ReviewSpecification();

        when(reviewDAO.getAll(reviewSpecification)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), reviewServiceImpl.findAll(reviewSpecification));

    }

    @Test
    public void findById() {
        when(reviewDAO.getById(1)).thenReturn(Optional.of(expectedReview));
        assertEquals(Optional.of(expectedReview), reviewServiceImpl.findById(1));
    }

    @Test
    public void findByWrongId() {
        when(reviewDAO.getById(-1)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), reviewServiceImpl.findById(-1));
    }

    @Test
    public void delete() {
        when(reviewDAO.delete(1)).thenReturn(true);
//        when(reviewDAO.getById(1)).thenReturn(Optional.of(expectedReview));
        assertTrue(reviewServiceImpl.delete(1));
    }

    @Test
    public void deleteFail() {
//        when(reviewDAO.getById(1)).thenReturn(Optional.of(expectedReview));
//        when(reviewDAO.delete(1)).thenReturn(0);
        assertFalse(reviewServiceImpl.delete(1));
    }

    @Test
    public void deleteByWrongId() {
//        when(reviewDAO.getById(-1)).thenReturn(Optional.empty());
        assertFalse(reviewServiceImpl.delete(-1));
    }

    @Test
    public void add() {
        when(reviewDAO.create(expectedReview)).thenReturn(expectedReview);
        Review actualReview = reviewServiceImpl.add(expectedReview);
        expectedReview.setId(1001);
        assertEquals(expectedReview, actualReview);
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void addByNull() {
        reviewServiceImpl.add(null);
    }

    @Test
    public void update() {
        when(reviewDAO.update(expectedReview)).thenReturn(expectedReview);
        assertEquals(expectedReview, reviewServiceImpl.update(expectedReview, 1));
    }

    @Test(expected = EntityNullValueRuntimeException.class)
    public void updateByNull() {
        reviewServiceImpl.update(null, 1);
    }
}
