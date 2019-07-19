package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.Tour;

import java.util.List;
import java.util.Optional;

public interface TourDAO {

    List<Tour> getAll();

    Optional<Tour> getById(int id);

    int delete(int id);

    int create(Tour tour);

    Optional<Tour> update(Tour tour, int id);
}
