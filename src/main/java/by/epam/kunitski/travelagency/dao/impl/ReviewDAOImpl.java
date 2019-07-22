package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.ReviewDAO;
import by.epam.kunitski.travelagency.entity.Review;
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
public class ReviewDAOImpl implements ReviewDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Review> ROW_MAPPER_REVIEW;

    private static final String SQL_GET_ALL = "SELECT * FROM review";
    private static final String SQL_GET_BY_ID = "SELECT * FROM review WHERE id = ?";
    private static final String SQL_CREATE = "insert into review (date, text, user_id, tour_id) values (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update review set date = ?, text = ?, user_id = ?, tour_id = ? where id = ?;";
    private static final String SQL_DELETE = "delete from review where id = ?;";

    @Override
    public List<Review> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_REVIEW);
    }

    @Override
    public Optional<Review> getById(int id) {
        List<Review> reviewList = jdbcTemplate.query(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_REVIEW);
        return reviewList.isEmpty() ? Optional.empty() : Optional.of(reviewList.get(0));
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public Review create(Review review) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pst =
                                con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                        pst.setDate(1, Date.valueOf(review.getDate()));
                        pst.setString(2, review.getText());
                        pst.setInt(3, review.getUserID());
                        pst.setInt(4, review.getTourID());
                        return pst;
                    }
                },
                keyHolder);
        if (keyHolder.getKey() != null) {
            review.setId(keyHolder.getKey().intValue());
        }
        return review;
    }

    @Override
    public Optional<Review> update(Review review, int id) {
        jdbcTemplate.update(SQL_UPDATE, review.getDate(), review.getText(), review.getUserID(), review.getTourID(), id);
        return getById(id);
    }

}
