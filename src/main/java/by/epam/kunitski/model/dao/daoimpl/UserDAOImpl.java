package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.daointerface.UserDAO;
import by.epam.kunitski.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAOImpl implements UserDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public User getById(int id) {
        User user = null;
        try {
            user = (User) jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_USER);
        } catch (DataAccessException e) {
            LOGGER.error("Couldn't find entity of type User with id " + id);
        }
        return user;

    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public boolean create(User user) {
        if (user != null) {
            jdbcTemplate.update(SQL_CREATE, user.getLogin(), user.getPassword());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User update(User user, int id) {
        if (user != null) {
            jdbcTemplate.update(SQL_UPDATE, user.getLogin(), user.getPassword(), id);
            return getById(id);
        } else return null;
    }
}
