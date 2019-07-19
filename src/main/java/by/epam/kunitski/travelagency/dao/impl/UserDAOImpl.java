package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.UserDAO;
import by.epam.kunitski.travelagency.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

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
        List<User> userList = jdbcTemplate.query(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_USER);
        return userList.isEmpty() ? Optional.empty() : Optional.of(userList.get(0));
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public int create(User user) {
        return jdbcTemplate.update(SQL_CREATE, user.getLogin(), user.getPassword());
    }

    @Override
    public Optional<User> update(User user, int id) {
        jdbcTemplate.update(SQL_UPDATE, user.getLogin(), user.getPassword(), id);
        return getById(id);
    }
}
