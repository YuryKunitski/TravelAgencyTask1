package by.epam.kunitski.travelagency.service;


import by.epam.kunitski.travelagency.entity.Tour;

import java.util.List;
import java.util.Optional;

public interface TourService extends EntityService<Tour> {

    @Override
    List<Tour> findAll();

    @Override
    Optional<Tour> findById(int id);

    @Override
    boolean delete(int id);

    @Override
    boolean add(Tour entity);

    @Override
    Optional<Tour> update(Tour entity, int id);
}
