package by.epam.kunitski.travelagency.dao.mapper;

import by.epam.kunitski.travelagency.entity.Hotel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HotelMapper implements RowMapper<Hotel> {
    @Override
    public Hotel mapRow(ResultSet resultSet, int i) throws SQLException {

        Hotel hotel = new Hotel();

        hotel.setId(resultSet.getInt("id"));
        hotel.setName(resultSet.getString("name"));
        hotel.setStars(resultSet.getInt("stars"));
        hotel.setWebsite(resultSet.getString("website"));
        hotel.setLatitude(resultSet.getDouble("latitude"));
        hotel.setLongitude(resultSet.getDouble("longitude"));
        hotel.setFeatures(Hotel.FeatureType.valueOf(resultSet.getString("features")));

        return hotel;
    }
}
