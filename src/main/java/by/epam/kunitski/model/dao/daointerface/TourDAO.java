package by.epam.kunitski.model.dao.daointerface;

import by.epam.kunitski.model.entity.Tour;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface TourDAO {
    RowMapper ROW_MAPPER_TOUR = (ResultSet rs, int rowNum) -> {
        return new Tour(rs.getInt(1), rs.getString(2), rs.getDate(3),
                rs.getInt(4), rs.getString(5), rs.getDouble(6),
                rs.getInt(7), rs.getInt(7), Tour.TourType.valueOf(rs.getString(8)));
    };

    List<Tour> getAll();

    Tour getById(int id);

    int delete(int id);

    boolean create(Tour tour);

    Tour update(Tour tour, int id);
}
