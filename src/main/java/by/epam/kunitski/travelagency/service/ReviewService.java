package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService extends EntityService<Review> {

    List<Review> findAll();

    Optional<Review> findById(int id);

    boolean delete(int id);

    boolean add(Review entity);

    Optional<Review> update(Review entity, int id);
}
