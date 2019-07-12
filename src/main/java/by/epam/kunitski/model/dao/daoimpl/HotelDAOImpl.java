package by.epam.kunitski.model.dao.daoimpl;

import by.epam.kunitski.model.dao.daointerface.HotelDAO;
import by.epam.kunitski.model.dao.dbconfig.DBConfig;
import by.epam.kunitski.model.entity.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelDAOImpl implements HotelDAO {

    private static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL = "SELECT * FROM hotel";
    private static final String SQL_GET_BY_ID = "SELECT * FROM hotel WHERE id = ?";
    private static final String SQL_CREATE = "insert into hotel (name, stars, website, latitude, longitude, features) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update hotel set name = ?, stars = ?, website = ?, latitude = ?, longitude = ?, features = ? where id = ?;";
    private static final String SQL_DELETE = "delete from hotel where id = ?;";

    @Override
    public List<Hotel> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, ROW_MAPPER_HOTEL);
    }

    @Override
    public Hotel getById(int id) {
        Hotel hotel = null;
        try {
            hotel = (Hotel) jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, ROW_MAPPER_HOTEL);
        } catch (DataAccessException e) {
            LOGGER.error("Couldn't find entity of type Hotel with id " + id);
        }
        return hotel;

    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public boolean create(Hotel hotel) {
        if (hotel != null) {
            jdbcTemplate.update(SQL_CREATE, hotel.getName(), hotel.getStars(), hotel.getWebsite(), hotel.getLatitude(),
                    hotel.getLongitude(), hotel.getFeatures().toString());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Hotel update(Hotel hotel, int id) {
        if (hotel != null) {
            jdbcTemplate.update(SQL_UPDATE, hotel.getName(), hotel.getStars(), hotel.getWebsite(), hotel.getLatitude(),
                    hotel.getLongitude(), hotel.getFeatures().toString(), id);
            return getById(id);
        } else return null;
    }

    public static void main(String[] args) {

        Hotel hotel = new Hotel(101, "Zimbabveshka", 5, "dfdf.com", 32.23
                , 54.45, Hotel.FeatureType.FREE_WIFI);

        ApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
        HotelDAO hotelDAO = context.getBean(HotelDAO.class);
        System.out.println(hotelDAO.create(hotel));
    }


}
