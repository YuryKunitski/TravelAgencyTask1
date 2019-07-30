package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.entity.Tour;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("tourDAOImpl")
public class TourDAOImpl extends AbstractEntityDao<Tour> {

    public TourDAOImpl(){
        super(Tour.class);
    }

    @Override
    public List<Tour> getAll() {
        return super.getAll();
    }

    @Override
    public Optional<Tour> getById(int id) {
        return super.getById(id);
    }

    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    public Tour create(Tour tour) {
        return super.create(tour);
    }

    @Override
    public Tour update(Tour tour) {
        return super.update(tour);
    }
}
