package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.dao.entity.Review;

import java.util.List;

public interface ReviewService extends EntityService<Review> {

    List<Review> findAllByUserId(int userId);

    List<Review> findAllByTourId(int tourId);
}
