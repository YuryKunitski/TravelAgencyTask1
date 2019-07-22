package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.UserDAO;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.entity.User;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<User> ROW_MAPPER_USER;

    private static final String SQL_GET_ALL = "SELECT * FROM public.\"user\"";
    private static final String SQL_GET_BY_ID = "SELECT * FROM public.\"user\" WHERE id = ?";
    private static final String SQL_CREATE = "insert into \"user\" (login, password) values (?, ?)";
    private static final String SQL_UPDATE = "update \"user\" set login = ?, password = ? where id = ?;";
    private static final String SQL_DELETE = "delete from \"user\" where id = ?;";

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_USER);
    }

    @Override
    public Optional<User> getById(int id) {

        Optional<User> userOptional = null;

        try {
            userOptional = Optional.of(jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_USER));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Couldn't find user with id " + id);
            userOptional = Optional.empty();
        }
        return userOptional;
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pst =
                                con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                        pst.setString(1, user.getLogin());
                        pst.setString(2, user.getPassword());
                        return pst;
                    }
                },
                keyHolder);
        if (keyHolder.getKey() != null) {
            user.setId(keyHolder.getKey().intValue());
        }
        return user;
    }

    @Override
    public Optional<User> update(User user, int id) {
        jdbcTemplate.update(SQL_UPDATE, user.getLogin(), user.getPassword(), id);
        return getById(id);
    }
}
