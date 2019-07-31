package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Hotel;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository("hotelDAOImpl")
public class HotelDAOImpl extends AbstractEntityDao<Hotel> {

    public HotelDAOImpl() {
        super(Hotel.class);
    }

    @Override
    public List<Hotel> getAll(Specification<Hotel> hotelSpecification) {
        return super.getAll(hotelSpecification);
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
