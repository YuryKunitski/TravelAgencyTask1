package by.epam.kunitski.model.dao.daointerface;

import by.epam.kunitski.model.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface CountryDAO {

    RowMapper ROW_MAPPER_COUNTRY = (ResultSet rs, int rowNum) -> {
        return new Country(rs.getInt(1), rs.getString(2));
    };

    List<Country> getAll();

    Country getById(int id);

    int delete(int id);

    boolean create(Country country);

    Country update(Country country, int id);
}
