package by.epam.kunitski.model.service.impl;

import by.epam.kunitski.model.dao.daoimpl.HotelDAOImpl;
import by.epam.kunitski.model.entity.Hotel;
import by.epam.kunitski.model.service.interf.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class HotelService implements EntityService<Hotel> {

    private final static Logger LOGGER = LoggerFactory.getLogger(HotelService.class);

    @Inject
    private HotelDAOImpl hotelDAO;

    @Override
    public List<Hotel> findAll() {
        LOGGER.info("Start method findAll");

        return hotelDAO.getAll();
    }

    @Override
    public Optional<Hotel> findById(int id) {
        LOGGER.info("Start method findById");

        return hotelDAO.getById(id);
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start method delete");

        if (hotelDAO.getById(id).isPresent()) {
            return hotelDAO.delete(id) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(Hotel entity) {
        LOGGER.info("Start method add");

        if (entity == null || hotelDAO.getById(entity.getId()).isPresent()) {
            return false;
        } else {
            return hotelDAO.create(entity) > 0;
        }
    }

    @Override
    public Optional<Hotel> update(Hotel entity, int id) {
        LOGGER.info("Start method update");

        if (entity != null) {
            return hotelDAO.update(entity, id);
        } else {
            return Optional.empty();
        }
    }
}
