package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.entity.*;

import java.time.LocalDate;

import static by.epam.kunitski.travelagency.dao.entity.Hotel.FeatureType.CHILDREN_AREA;
import static by.epam.kunitski.travelagency.dao.entity.Tour.TourType.ECONOM;

public class InitEntity {

    static Country initCountry() {
        Country country = new Country();

        country.setName("Belarus");

        return country;
    }

    static Hotel initHotel() {
        Hotel hotel = new Hotel();

        hotel.setName("Turist");
        hotel.setStars(2);
        hotel.setWebsite("kvassman0@wikimedia.org");
        hotel.setLatitude(8.2673715);
        hotel.setLongitude(48.9086571);
        hotel.setFeatures(CHILDREN_AREA);

        return hotel;
    }

    static Review initReview() {
        Review review = new Review();

        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Choloepus hoffmani");
        hotel.setStars(2);
        hotel.setWebsite("kvassman0@wikimedia.org");
        hotel.setLatitude(8.2673715);
        hotel.setLongitude(48.9086571);
        hotel.setFeatures(CHILDREN_AREA);

        Country country = new Country();
        country.setId(1);
        country.setName("China");


        User user = new User();
        user.setId(1);
        user.setLogin("Saundra");
        user.setPassword("CDHjDf5Tnr");

        Tour tour = new Tour();
        tour.setId(1);
        tour.setPhoto("http://dummyimage.com/190x216.jpg/ff4444/ffffff");
        tour.setDate(LocalDate.of(2019, 8, 14));
        tour.setDuration(1);
        tour.setDescription("Integer non velit.");
        tour.setCost(1839.65);
        tour.setHotel_id(hotel);
        tour.setCountry_id(country);
        tour.setTour_type(ECONOM);

        review.setText("Excelent");
        review.setDate(LocalDate.of(2019, 1, 1));
        review.setText("Pellentesque ultrices mattis odio.");
        review.setUserID(user);
        review.setTourID(tour);

        return review;
    }

    static Tour initTour() {
        Tour tour = new Tour();

        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Choloepus hoffmani");
        hotel.setStars(2);
        hotel.setWebsite("kvassman0@wikimedia.org");
        hotel.setLatitude(8.2673715);
        hotel.setLongitude(48.9086571);
        hotel.setFeatures(CHILDREN_AREA);

        Country country = new Country();
        country.setId(1);
        country.setName("China");

        tour.setPhoto("http://dummyimage.com/190x216.jpg/ff4444/ffffff");
        tour.setDate(LocalDate.of(2019, 8, 14));
        tour.setDuration(1);
        tour.setDescription("Integer non velit.");
        tour.setCost(1839.65);
        tour.setHotel_id(hotel);
        tour.setCountry_id(country);
        tour.setTour_type(ECONOM);

        return tour;
    }

    static User initUser() {
        User user = new User();

        user.setLogin("Saundra");
        user.setPassword("CDHjDf5Tnr");

        return user;
    }

}
