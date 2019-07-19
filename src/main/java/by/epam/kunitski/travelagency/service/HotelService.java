package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService extends EntityService<Hotel> {

    @Override
    List<Hotel> findAll();

    @Override
    Optional<Hotel> findById(int id);

    @Override
    boolean delete(int id);

    @Override
    boolean add(Hotel entity);

    @Override
    Optional<Hotel> update(Hotel entity, int id);
}
