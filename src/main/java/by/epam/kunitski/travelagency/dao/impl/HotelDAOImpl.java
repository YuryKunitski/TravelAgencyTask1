package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.HotelDAO;
import by.epam.kunitski.travelagency.entity.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class HotelDAOImpl implements HotelDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(HotelDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Hotel> ROW_MAPPER_HOTEL;

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

        Optional<Hotel> hotelOptional = null;

        try {
            hotelOptional = Optional.of(jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_HOTEL));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Couldn't find hotel with id " + id);
            hotelOptional = Optional.empty();
        }
        return hotelOptional;
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public Hotel create(Hotel hotel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pst =
                                con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                        pst.setString(1, hotel.getName());
                        pst.setInt(2, hotel.getStars());
                        pst.setString(3, hotel.getWebsite());
                        pst.setDouble(4, hotel.getLatitude());
                        pst.setDouble(5, hotel.getLongitude());
                        pst.setString(6, hotel.getFeatures().toString());
                        return pst;
                    }
                },
                keyHolder);
        if (keyHolder.getKey() != null) {
            hotel.setId(keyHolder.getKey().intValue());
        }
        return hotel;
    }

    @Override
    public Optional<Hotel> update(Hotel hotel, int id) {
        jdbcTemplate.update(SQL_UPDATE, hotel.getName(), hotel.getStars(), hotel.getWebsite(), hotel.getLatitude(),
                hotel.getLongitude(), hotel.getFeatures().toString(), id);
        return getById(id);
    }

}
