package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.ReviewDAO;
import by.epam.kunitski.travelagency.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewDAOImpl implements ReviewDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReviewDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL = "SELECT * FROM review";
    private static final String SQL_GET_BY_ID = "SELECT * FROM review WHERE id = ?";
    private static final String SQL_CREATE = "insert into review (date, text, user_id, tour_id) values (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update review set date = ?, text = ?, user_id = ?, tour_id = ? where id = ?;";
    private static final String SQL_DELETE = "delete from review where id = ?;";

    @Override
    public List<Review> getAll() {
        LOGGER.info("Start method getAll");
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_REVIEW);
    }

    @Override
    public Optional<Review> getById(int id) {
        LOGGER.info("Start method getById");
        List<Review> reviewList = jdbcTemplate.query(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_REVIEW);
        return reviewList.isEmpty() ? Optional.empty() : Optional.of(reviewList.get(0));
    }

    @Override
    public int delete(int id) {
        LOGGER.info("Start method delete");
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public int create(Review review) {
        LOGGER.info("Start method create");
        return jdbcTemplate.update(SQL_CREATE, review.getDate(), review.getText(), review.getUserID(), review.getTourID());
    }

    @Override
    public Optional<Review> update(Review review, int id) {
        LOGGER.info("Start method update");
        jdbcTemplate.update(SQL_UPDATE, review.getDate(), review.getText(), review.getUserID(), review.getTourID(), id);
        return getById(id);
    }

}
