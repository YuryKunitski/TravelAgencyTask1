package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDAO {

    List<Country> getAll();

    Optional<Country> getById(int id);

    boolean delete(int id);

    Country create(Country country);

    Country update(Country country);
}
