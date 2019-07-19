package by.epam.kunitski.travelagency.dao.mapper;

import by.epam.kunitski.travelagency.entity.Tour;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TourMapper implements RowMapper<Tour> {

    @Override
    public Tour mapRow(ResultSet resultSet, int i) throws SQLException {
        Tour tour = new Tour();

        tour.setId(resultSet.getInt("id"));
        tour.setPhoto(resultSet.getString("photo"));
        tour.setDate(resultSet.getDate("date").toLocalDate());
        tour.setDuration(resultSet.getInt("duration"));
        tour.setDescription(resultSet.getString("description"));
        tour.setCost(resultSet.getDouble("cost"));
        tour.setTour_type(Tour.TourType.valueOf(resultSet.getString("tour_type")));
        tour.setHotel_id(resultSet.getInt("hotel_id"));
        tour.setCountry_id(resultSet.getInt("country_id"));

        return tour;
    }
}
