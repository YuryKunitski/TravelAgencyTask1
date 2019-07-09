package by.epam.kunitski.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tour {

    private int id;
    private String photo;
    private Date date;
    private int duration;
    private String description;
    private Double cost;
    private int hotel_id;
    private int country_id;
    private TourType tour_type;

   public enum TourType {
        ECONOM, ALL_INCLUSIVE, ONLY_BREAKFAST
    }

}
