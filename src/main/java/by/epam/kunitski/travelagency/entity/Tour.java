package by.epam.kunitski.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tour")
public class Tour {

    @Id
    @GeneratedValue
    private int id;
    private String photo;
    private LocalDate date;
    private int duration;
    private String description;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private int hotel_id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private int country_id;
    private TourType tour_type;

   public enum TourType {
        ECONOM, ALL_INCLUSIVE, ONLY_BREAKFAST
    }

}
