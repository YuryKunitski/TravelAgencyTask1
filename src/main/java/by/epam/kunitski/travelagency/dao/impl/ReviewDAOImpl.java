package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.ReviewDAO;
import by.epam.kunitski.travelagency.entity.Review;
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

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(ReviewDAOImpl.class);

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

        Optional<Review> reviewOptional = null;

        try {
            reviewOptional = Optional.of(jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_REVIEW));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Couldn't find review with id " + id);
            reviewOptional = Optional.empty();
        }
        return reviewOptional;
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
