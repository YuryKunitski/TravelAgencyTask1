package by.epam.kunitski.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = "tourList")
@EqualsAndHashCode(exclude = "tourList")
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int stars;
    private String website;
    private double latitude;
    private double longitude;

    @Enumerated(EnumType.STRING)
    private FeatureType features;

    @OneToMany(mappedBy = "hotel_id", cascade=CascadeType.ALL)
    private Set<Tour> tourList = new HashSet<>();;

   public enum FeatureType{
        SWIMMING_POOL, FREE_WIFI, PARKING, CHILDREN_AREA, RESTAURANT
    }

}
