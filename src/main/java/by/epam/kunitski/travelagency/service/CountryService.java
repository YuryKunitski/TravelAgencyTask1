package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService extends EntityService<Country> {

    List<Country> findAll();

    Optional<Country> findById(int id);

    boolean delete(int id);

    boolean add(Country entity);

    Optional<Country> update(Country entity, int id);
}
