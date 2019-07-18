package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.Tour;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface TourDAO {
    RowMapper ROW_MAPPER_TOUR = (ResultSet rs, int rowNum) -> {
        return new Tour(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(),
                rs.getInt(4), rs.getString(5), rs.getDouble(6),
                rs.getInt(7), rs.getInt(8), Tour.TourType.valueOf(rs.getString(9)));
    };

    List<Tour> getAll();

    Optional<Tour> getById(int id);

    int delete(int id);

    int create(Tour tour);

    Optional<Tour> update(Tour tour, int id);
}
