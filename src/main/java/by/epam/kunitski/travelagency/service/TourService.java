package by.epam.kunitski.travelagency.service;


import by.epam.kunitski.travelagency.entity.Tour;

import java.util.List;

public interface TourService extends EntityService<Tour> {

    List<Tour> findAllByUserId(int userId);

}
