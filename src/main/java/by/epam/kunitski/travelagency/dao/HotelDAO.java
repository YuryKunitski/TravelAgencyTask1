package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelDAO {

    List getAll();

    Optional<Hotel> getById(int id);

    int delete(int id);

    Hotel create(Hotel hotel);

    Optional<Hotel> update(Hotel hotel, int id);
}
