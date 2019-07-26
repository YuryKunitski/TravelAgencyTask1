package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelDAO {

    List getAll();

    Optional<Hotel> getById(int id);

    boolean delete(int id);

    Hotel create(Hotel hotel);

    Hotel update(Hotel hotel);
}
