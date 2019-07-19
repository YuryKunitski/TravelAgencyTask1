package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService extends EntityService<Hotel> {

    List<Hotel> findAll();

    Optional<Hotel> findById(int id);

    boolean delete(int id);

    boolean add(Hotel entity);

    Optional<Hotel> update(Hotel entity, int id);
}
