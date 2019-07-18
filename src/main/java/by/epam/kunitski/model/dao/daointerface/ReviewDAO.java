package by.epam.kunitski.model.dao.daointerface;

import by.epam.kunitski.model.entity.Review;
import by.epam.kunitski.model.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReviewDAO {
    RowMapper ROW_MAPPER_REVIEW = (ResultSet rs, int rowNum) -> {
        return new Review(rs.getInt(1),  rs.getDate(2).toLocalDate(), rs.getString(3), rs.getInt(4), rs.getInt(5));
    };

    List<Review> getAll();

    Optional<Review> getById(int id);

    int delete(int id);

    int create(Review review);

    Optional<Review> update(Review review, int id);
}
