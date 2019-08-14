package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.ReviewDAOImpl;
import by.epam.kunitski.travelagency.dao.specification.impl.ReviewSpecification;
import by.epam.kunitski.travelagency.entity.Review;
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
public class ReviewServiceImplTest {

    private Review expectedReview = new Review();

    @Mock
    private Validator validator;

    @Mock
    Set<ConstraintViolation<Review>> expViolations;

    @Mock
    private ReviewDAOImpl reviewDAO;

    @InjectMocks
    private ReviewServiceImpl reviewServiceImpl;

    @Test
    public void findAll() {
        when(reviewDAO.getAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), reviewServiceImpl.findAll());
    }

    @Test
    public void findAllByTourId() {
        when(reviewDAO.getAllByTourId(1)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), reviewServiceImpl.findAllByTourId(1));
    }

    @Test
    public void findAllByUserId() {
        when(reviewDAO.getAllByUserId(1)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), reviewServiceImpl.findAllByUserId(1));
    }

    @Test
    public void findAllByCriteria() {
        ReviewSpecification reviewSpecification = new ReviewSpecification();

        when(reviewDAO.getAllByCriteria(reviewSpecification)).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), reviewServiceImpl.findAllByCriteria(reviewSpecification));
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
        assertTrue(reviewServiceImpl.delete(1));
    }

    @Test
    public void deleteFail() {
        when(reviewDAO.delete(1)).thenReturn(false);
        assertFalse(reviewServiceImpl.delete(1));
    }

    @Test
    public void addValid() {
        when(validator.validate(expectedReview)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(true);
        when(reviewDAO.create(expectedReview)).thenReturn(expectedReview);

        reviewServiceImpl.add(expectedReview);

        verify(reviewDAO, times(1)).create(expectedReview);
    }

    @Test
    public void addNotValid() {
        when(validator.validate(expectedReview)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(false);

        reviewServiceImpl.add(expectedReview);

        verify(reviewDAO, times(0)).create(expectedReview);
    }

    @Test
    public void addByNull() {
        reviewServiceImpl.add(null);
    }

    @Test
    public void updateValid() {
        when(validator.validate(expectedReview)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(true);
        when(reviewDAO.update(expectedReview)).thenReturn(expectedReview);

        reviewServiceImpl.update(expectedReview);

        verify(reviewDAO, times(1)).update(expectedReview);
    }

    @Test
    public void updateNotValid() {
        when(validator.validate(expectedReview)).thenReturn(expViolations);
        when(expViolations.isEmpty()).thenReturn(false);

        reviewServiceImpl.update(expectedReview);

        verify(reviewDAO, times(0)).update(expectedReview);
    }

    @Test
    public void updateByNull() {
        reviewServiceImpl.update(null);
    }
}
