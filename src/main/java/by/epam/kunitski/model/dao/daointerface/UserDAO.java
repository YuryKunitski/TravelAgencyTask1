package by.epam.kunitski.model.dao.daointerface;

import by.epam.kunitski.model.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface UserDAO {

    RowMapper ROW_MAPPER_USER = (ResultSet rs, int rowNum) -> {
        return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
    };

    List<User> getAll();

    User getById(int id);

    int delete(int id);

    boolean create(User user);

    User update(User user, int id);
}
