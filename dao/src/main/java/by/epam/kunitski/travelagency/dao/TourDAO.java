package by.epam.kunitski.travelagency.dao;

import by.epam.kunitski.travelagency.dao.entity.Tour;

import java.util.List;

public interface TourDAO extends EntityDAO<Tour> {

    List<Tour> getAllByUserId(int userId);

}
