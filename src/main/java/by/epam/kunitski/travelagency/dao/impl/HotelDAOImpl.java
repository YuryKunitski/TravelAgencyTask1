package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.HotelDAO;
import by.epam.kunitski.travelagency.entity.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Repository
public class HotelDAOImpl extends AbstractEntityDao<Hotel> implements HotelDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(HotelDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hotel> getAll() {
        return super.getAll(Hotel.class);
    }

    @Override
    public Optional<Hotel> getById(int id) {
        return Optional.ofNullable(entityManager.find(Hotel.class, id));
    }

    @Override
    public boolean delete(int id) {

        boolean result = false;

        try {
            Hotel hotel = getById(id).get();
            entityManager.remove(hotel);
            result = true;
        } catch (NoSuchElementException e) {
            LOGGER.error("Couldn't delete hotel with id" + id);
        }
        return result;
    }

    @Override
    public Hotel create(Hotel hotel) {
        entityManager.persist(hotel);
        entityManager.flush();
        return hotel;
    }

    @Override
    public Hotel update(Hotel hotel) {
        return entityManager.merge(hotel);
    }

}
