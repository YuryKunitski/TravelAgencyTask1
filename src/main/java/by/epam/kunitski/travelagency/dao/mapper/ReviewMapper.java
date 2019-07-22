package by.epam.kunitski.travelagency.dao.mapper;

import by.epam.kunitski.travelagency.entity.Review;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {

        Review review = new Review();

        review.setId(resultSet.getInt("id"));
        review.setDate(resultSet.getDate("date").toLocalDate());
        review.setText(resultSet.getString("text"));
        review.setUserID(resultSet.getInt("user_id"));
        review.setTourID(resultSet.getInt("tour_id"));

        return review;
    }
}
