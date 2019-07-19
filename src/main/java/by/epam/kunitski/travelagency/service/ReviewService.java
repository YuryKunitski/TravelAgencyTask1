package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService extends EntityService<Review> {

    @Override
    List<Review> findAll();

    @Override
    Optional<Review> findById(int id);

    @Override
    boolean delete(int id);

    @Override
    boolean add(Review entity);

    @Override
    Optional<Review> update(Review entity, int id);
}
