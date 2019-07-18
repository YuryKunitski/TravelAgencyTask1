package by.epam.kunitski.model.dao.daointerface;

import by.epam.kunitski.model.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface CountryDAO {

    RowMapper ROW_MAPPER_COUNTRY = (ResultSet rs, int rowNum) -> {
        return new Country(rs.getInt(1), rs.getString(2));
    };

    List<Country> getAll();

    Optional<Country> getById(int id);

    int delete(int id);

    int create(Country country);

    Optional<Country> update(Country country, int id);
}
