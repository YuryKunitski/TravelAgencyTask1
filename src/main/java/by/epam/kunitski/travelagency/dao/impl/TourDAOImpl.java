package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.TourDAO;
import by.epam.kunitski.travelagency.entity.Tour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class TourDAOImpl implements TourDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(TourDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Tour> ROW_MAPPER_TOUR;

    private static final String SQL_GET_ALL = "SELECT * FROM public.tour";
    private static final String SQL_GET_BY_ID = "SELECT * FROM public.tour WHERE id = ?";
    private static final String SQL_CREATE = "insert into tour (photo, date, duration, description, cost, hotel_id," +
            " country_id, tour_type) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update tour set photo = ?, date = ?, duration = ?, description = ?," +
            " cost = ?, hotel_id = ?, country_id = ?, tour_type = ? where id = ?;";
    private static final String SQL_DELETE = "delete from tour where id = ?;";


    public List<Tour> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_TOUR);
    }

    @Override
    public Optional<Tour> getById(int id) {
        List<Tour> tourList = jdbcTemplate.query(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_TOUR);
        return tourList.isEmpty() ? Optional.empty() : Optional.of(tourList.get(0));

    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public Tour create(Tour tour) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pst =
                                con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                        pst.setString(1, tour.getPhoto());
                        pst.setDate(2, Date.valueOf(tour.getDate()));
                        pst.setInt(3, tour.getDuration());
                        pst.setString(4, tour.getDescription());
                        pst.setDouble(5, tour.getCost());
                        pst.setInt(6, tour.getHotel_id());
                        pst.setInt(7, tour.getCountry_id());
                        pst.setString(8, tour.getTour_type().toString());
                        return pst;
                    }
                },
                keyHolder);
        if (keyHolder.getKey() != null) {
            tour.setId(keyHolder.getKey().intValue());
        }
        return tour;
    }

    @Override
    public Optional<Tour> update(Tour tour, int id) {
        jdbcTemplate.update(SQL_UPDATE, tour.getPhoto(), tour.getDate(), tour.getDuration(), tour.getDescription(),
                tour.getCost(), tour.getHotel_id(), tour.getCountry_id(), tour.getTour_type().toString(), id);
        return getById(id);
    }
}
