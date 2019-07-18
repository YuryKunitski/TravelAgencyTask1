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
import java.util.Optional;

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
        LOGGER.info("Start method getAll");
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_USER);
    }

    @Override
    public Optional<User> getById(int id) {
        LOGGER.info("Start method getById");
        List<User> userList = jdbcTemplate.query(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_USER);
        return userList.isEmpty() ? Optional.empty() : Optional.of(userList.get(0));
    }

    @Override
    public int delete(int id) {
        LOGGER.info("Start method delete");
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public int create(User user) {
        LOGGER.info("Start method create");
        return jdbcTemplate.update(SQL_CREATE, user.getLogin(), user.getPassword());
    }

    @Override
    public Optional<User> update(User user, int id) {
        LOGGER.info("Start method update");
            jdbcTemplate.update(SQL_UPDATE, user.getLogin(), user.getPassword(), id);
            return getById(id);
    }
}
