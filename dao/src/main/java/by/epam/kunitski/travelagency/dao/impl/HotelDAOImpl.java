package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.HotelDAO;
import by.epam.kunitski.travelagency.dao.entity.Hotel;
import org.springframework.stereotype.Repository;

@Repository

public class HotelDAOImpl extends AbstractEntityDao<Hotel> implements HotelDAO {

    public HotelDAOImpl() {
        super(Hotel.class);
    }

}
