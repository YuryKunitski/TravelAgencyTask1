package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.daointerface.CountryDAO;
import by.epam.kunitski.model.dao.daointerface.ReviewDAO;
import by.epam.kunitski.model.dao.dbpool.DBConfig;
import by.epam.kunitski.model.entity.Country;
import by.epam.kunitski.model.entity.Review;
import by.epam.kunitski.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ReviewDAOImpl implements ReviewDAO {

    private static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public Review getById(int id) {
        Review review = null;
        try {
            review = (Review) jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_REVIEW);
        } catch (DataAccessException e) {
            LOGGER.error("Couldn't find entity of type Review with id " + id);
        }
        return review;

    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public boolean create(Review review) {
        if (review != null) {
            jdbcTemplate.update(SQL_CREATE, review.getDate(), review.getText(), review.getUserID(), review.getTourID());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review update(Review review, int id) {
        jdbcTemplate.update(SQL_UPDATE, review.getDate(), review.getText(), review.getUserID(), review.getTourID(), id);
        return getById(id);
    }

    public static void main(String[] args) {

        Review review = new Review();
        review.setDate(new Date(1));
        review.setText("aaaa");
        review.setUserID(1);
        review.setTourID(1);

        ApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
        ReviewDAO reviewDAO = context.getBean(ReviewDAOImpl.class);
        System.out.println(reviewDAO.update(review, 1));
    }

}
