package by.epam.kunitski.travelagency.service;

import by.epam.kunitski.travelagency.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService extends EntityService<Country> {

    @Override
    List<Country> findAll();

    @Override
    Optional<Country> findById(int id);

    @Override
    boolean delete(int id);

    @Override
    boolean add(Country entity);

    @Override
    Optional<Country> update(Country entity, int id);
}
