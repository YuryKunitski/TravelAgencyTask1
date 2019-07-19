package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDAO {

    List<Country> getAll();

    Optional<Country> getById(int id);

    int delete(int id);

    int create(Country country);

    Optional<Country> update(Country country, int id);
}
