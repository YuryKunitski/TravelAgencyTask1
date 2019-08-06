package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.EntityDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Hotel;
import by.epam.kunitski.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HotelServiceImpl implements HotelService {

    private Set<ConstraintViolation<Hotel>> violationsHotel;

    @Autowired
    private Validator validator;

    @Autowired
    @Qualifier("hotelDAOImpl")
    private EntityDAO<Hotel> hotelDAO;

    @Override
    public List<Hotel> findAll(Specification<Hotel> hotelSpecification) {
        return hotelDAO.getAll(hotelSpecification);
    }

    @Override
    public Optional<Hotel> findById(int id) {
        return hotelDAO.getById(id);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return hotelDAO.delete(id);
    }

    @Transactional
    @Override
    public Set<ConstraintViolation<Hotel>> add(Hotel hotel) {
        violationsHotel = validator.validate(hotel);

        if (violationsHotel.isEmpty()) {
            hotelDAO.create(hotel);
        }

        return violationsHotel;

    }

    @Transactional
    @Override
    public Set<ConstraintViolation<Hotel>> update(Hotel hotel) {
        violationsHotel = validator.validate(hotel);

        if (violationsHotel.isEmpty()) {
            hotelDAO.update(hotel);
        }

        return violationsHotel;
    }

}
