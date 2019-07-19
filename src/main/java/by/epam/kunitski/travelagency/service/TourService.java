package by.epam.kunitski.travelagency.service;


import by.epam.kunitski.travelagency.entity.Tour;

import java.util.List;
import java.util.Optional;

public interface TourService extends EntityService<Tour> {

    List<Tour> findAll();

    Optional<Tour> findById(int id);

    boolean delete(int id);

    boolean add(Tour entity);

    Optional<Tour> update(Tour entity, int id);
}
