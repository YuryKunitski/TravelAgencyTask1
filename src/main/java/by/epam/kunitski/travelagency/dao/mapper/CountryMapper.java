package by.epam.kunitski.travelagency.dao.mapper;

import by.epam.kunitski.travelagency.entity.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CountryMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();

        country.setId(resultSet.getInt("id"));
        country.setName(resultSet.getString("name"));

        return country;
    }
}
