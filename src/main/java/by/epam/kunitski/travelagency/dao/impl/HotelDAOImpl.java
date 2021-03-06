package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.HotelDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Hotel;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class HotelDAOImpl extends AbstractEntityDao<Hotel> implements HotelDAO {

    public HotelDAOImpl() {
        super(Hotel.class);
    }

    @Override
    public List<Hotel> getAll() {
        return super.getAll();
    }

    @Override
    public List<Hotel> getAllByCriteria(Specification<Hotel> hotelSpecification) {
        return super.getAllByCriteria(hotelSpecification);
    }

    @Override
    public Optional<Hotel> getById(int id) {
        return super.getById(id);
    }

    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    public Hotel create(Hotel hotel) {
        return super.create(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return super.update(hotel);
    }
}
