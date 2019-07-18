package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.Hotel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface HotelDAO {

    RowMapper ROW_MAPPER_HOTEL = (ResultSet rs, int rowNum) -> {
        return new Hotel(rs.getInt(1), rs.getString(2), rs.getInt(3),
                rs.getString(4), rs.getDouble(5), rs.getDouble(6),
                Hotel.FeatureType.valueOf(rs.getString(7)));
    };

    List getAll();

    Optional<Hotel> getById(int id);

    int delete(int id);

    int create(Hotel hotel);

    Optional<Hotel> update(Hotel hotel, int id);
}
