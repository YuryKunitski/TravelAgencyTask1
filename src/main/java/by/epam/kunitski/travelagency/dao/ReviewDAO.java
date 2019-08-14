package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.Review;

import java.util.List;

public interface ReviewDAO extends EntityDAO<Review> {

    List<Review> getAllByTourId(int tourId);

    List<Review> getAllByUserId(int userId);

}
