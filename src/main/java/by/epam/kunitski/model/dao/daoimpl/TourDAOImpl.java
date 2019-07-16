package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.daointerface.TourDAO;
import by.epam.kunitski.model.entity.Tour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourDAOImpl implements TourDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL = "SELECT * FROM public.tour";
    private static final String SQL_GET_BY_ID = "SELECT * FROM public.tour WHERE id = ?";
    private static final String SQL_CREATE = "insert into tour (photo, date, duration, description, cost, hotel_id," +
            " country_id, tour_type) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update tour set photo = ?, date = ?, duration = ?, description = ?," +
            " cost = ?, hotel_id = ?, country_id = ?, tour_type = ? where id = ?;";
    private static final String SQL_DELETE = "delete from tour where id = ?;";


    public List<Tour> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_TOUR);
    }

    @Override
    public Tour getById(int id) {
        Tour tour = null;
        try {
            tour = (Tour) jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_TOUR);
        } catch (DataAccessException e) {
            LOGGER.error("Couldn't find entity of type Tour with id " + id);
        }
        return tour;

    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public boolean create(Tour tour) {
        if (tour != null) {
            jdbcTemplate.update(SQL_CREATE, tour.getPhoto(), tour.getDate(), tour.getDuration(), tour.getDescription(),
                    tour.getCost(), tour.getHotel_id(), tour.getCountry_id(), tour.getTour_type().toString());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Tour update(Tour tour, int id) {
        if (tour != null) {
            jdbcTemplate.update(SQL_UPDATE, tour.getPhoto(), tour.getDate(), tour.getDuration(), tour.getDescription(),
                    tour.getCost(), tour.getHotel_id(), tour.getCountry_id(), tour.getTour_type().toString(), id);
            return getById(id);
        } else return null;
    }

}
