package by.epam.kunitski.model.dao.daointerface;

import by.epam.kunitski.model.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface UserDAO {

    RowMapper ROW_MAPPER_USER = (ResultSet rs, int rowNum) -> {
        return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
    };

    List<User> getAll();

    Optional<User> getById(int id);

    int delete(int id);

    int create(User user);

    Optional<User> update(User user, int id);
}
