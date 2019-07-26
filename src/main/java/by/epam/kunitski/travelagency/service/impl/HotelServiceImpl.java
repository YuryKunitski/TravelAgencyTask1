package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.HotelDAO;
import by.epam.kunitski.travelagency.entity.Hotel;
import by.epam.kunitski.travelagency.exception.EntityNullValueRuntimeException;
import by.epam.kunitski.travelagency.service.HotelService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class HotelServiceImpl implements HotelService {

    @Inject
    private HotelDAO hotelDAO;

    @Override
    public List<Hotel> findAll() {
        return hotelDAO.getAll();
    }

    @Override
    public Optional<Hotel> findById(int id) {
        return hotelDAO.getById(id);
    }

    @Override
    public boolean delete(int id) {
//        if (hotelDAO.getById(id).isPresent()) {
//            return hotelDAO.delete(id) > 0;
//        } else {
            return hotelDAO.delete(id);
//        }
    }

    @Override
    public Hotel add(Hotel hotel) {
        if (hotel != null) {
            return hotelDAO.create(hotel);
        } else {
            throw new EntityNullValueRuntimeException("Method add() of " + this.getClass() + " got input value 'hotel' is null");
        }
    }

    @Override
    public Hotel update(Hotel hotel, int id) {
        if (hotel != null) {
            return hotelDAO.update(hotel);
        } else {
            throw new EntityNullValueRuntimeException("Method update() of " + this.getClass() + " got input value 'hotel' is null");
        }
    }

}
