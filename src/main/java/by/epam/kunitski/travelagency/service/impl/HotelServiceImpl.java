package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.impl.HotelDAOImpl;
import by.epam.kunitski.travelagency.entity.Hotel;
import by.epam.kunitski.travelagency.service.HotelService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class HotelServiceImpl implements HotelService {

    @Inject
    private HotelDAOImpl hotelDAO;

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
        if (hotelDAO.getById(id).isPresent()) {
            return hotelDAO.delete(id) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(Hotel entity) {
        if (entity == null || hotelDAO.getById(entity.getId()).isPresent()) {
            return false;
        } else {
            return hotelDAO.create(entity) > 0;
        }
    }

    @Override
    public Optional<Hotel> update(Hotel entity, int id) {
        if (entity != null) {
            return hotelDAO.update(entity, id);
        } else {
            return Optional.empty();
        }
    }
}
