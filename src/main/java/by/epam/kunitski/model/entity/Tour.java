package by.epam.kunitski.model.entity;

import java.sql.Date;

public class Tour {

    private int id;
    private String photo;
    private Date date;
    private int duration;
    private String description;
    private Double cost;
    private int hotelID;
    private int countryID;
    private TourType tourType;

    enum TourType {
        ECONOM, ALL_INCLUSIVE, ONLY_BREAKFAST
    }

    public Tour(int id, String photo, Date date, int duration, String description, Double cost, int hotelID, int countryID, TourType tourType) {
        this.id = id;
        this.photo = photo;
        this.date = date;
        this.duration = duration;
        this.description = description;
        this.cost = cost;
        this.hotelID = hotelID;
        this.countryID = countryID;
        this.tourType = tourType;
    }
}
