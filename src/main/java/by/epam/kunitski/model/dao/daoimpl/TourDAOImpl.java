package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.daointerface.TourDAO;
import by.epam.kunitski.model.entity.Tour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourDAOImpl implements TourDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(TourDAOImpl.class);

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
        LOGGER.info("Start method getAll");
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_TOUR);
    }

    @Override
    public Optional<Tour> getById(int id) {
        LOGGER.info("Start method getById");
        List<Tour> tourList = jdbcTemplate.query(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_TOUR);
        return tourList.isEmpty() ? Optional.empty() : Optional.of(tourList.get(0));

    }

    @Override
    public int delete(int id) {
        LOGGER.info("Start method delete");
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public int create(Tour tour) {
        LOGGER.info("Start method create");
        return jdbcTemplate.update(SQL_CREATE, tour.getPhoto(), tour.getDate(), tour.getDuration(), tour.getDescription(),
                tour.getCost(), tour.getHotel_id(), tour.getCountry_id(), tour.getTour_type().toString());
    }

    @Override
    public Optional<Tour> update(Tour tour, int id) {
        LOGGER.info("Start method update");
        jdbcTemplate.update(SQL_UPDATE, tour.getPhoto(), tour.getDate(), tour.getDuration(), tour.getDescription(),
                tour.getCost(), tour.getHotel_id(), tour.getCountry_id(), tour.getTour_type().toString(), id);
        return getById(id);
    }
}
