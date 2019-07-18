package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.HotelDAO;
import by.epam.kunitski.travelagency.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HotelDAOImpl implements HotelDAO {

      @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL = "SELECT * FROM hotel";
    private static final String SQL_GET_BY_ID = "SELECT * FROM hotel WHERE id = ?";
    private static final String SQL_CREATE = "insert into hotel (name, stars, website, latitude, longitude, features) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update hotel set name = ?, stars = ?, website = ?, latitude = ?, longitude = ?, features = ? where id = ?;";
    private static final String SQL_DELETE = "delete from hotel where id = ?;";

    @Override
    public List<Hotel> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_HOTEL);
    }

    @Override
    public Optional<Hotel> getById(int id) {
        List<Hotel> hotelList = jdbcTemplate.query(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_HOTEL);
       return hotelList.isEmpty() ? Optional.empty() : Optional.of(hotelList.get(0));
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public int create(Hotel hotel) {
        return jdbcTemplate.update(SQL_CREATE, hotel.getName(), hotel.getStars(), hotel.getWebsite(), hotel.getLatitude(),
                hotel.getLongitude(), hotel.getFeatures().toString());
    }

    @Override
    public Optional<Hotel> update(Hotel hotel, int id) {
        jdbcTemplate.update(SQL_UPDATE, hotel.getName(), hotel.getStars(), hotel.getWebsite(), hotel.getLatitude(),
                hotel.getLongitude(), hotel.getFeatures().toString(), id);
        return getById(id);
    }

}
