package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.Tour;

import java.util.List;
import java.util.Optional;

public interface TourDAO {

    List<Tour> getAll();

    Optional<Tour> getById(int id);

    boolean delete(int id);

    Tour create(Tour tour);

    Tour update(Tour tour);
}
