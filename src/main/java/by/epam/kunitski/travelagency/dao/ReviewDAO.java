package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewDAO {

    List<Review> getAll();

    Optional<Review> getById(int id);

    int delete(int id);

    Review create(Review review);

    Optional<Review> update(Review review, int id);
}
