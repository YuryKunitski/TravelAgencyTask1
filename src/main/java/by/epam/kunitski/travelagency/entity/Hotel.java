package by.epam.kunitski.travelagency.entity;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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

    @NotNull
    private String name;

    @Range(min = 1, max = 5)
    private int stars;

    @Email
    private String website;

    @PositiveOrZero
    private double latitude;

    @PositiveOrZero
    private double longitude;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FeatureType features;

    @OneToMany(mappedBy = "hotel_id", cascade=CascadeType.ALL)
    private Set<Tour> tourList = new HashSet<>();;

   public enum FeatureType{
        SWIMMING_POOL, FREE_WIFI, PARKING, CHILDREN_AREA, RESTAURANT
    }

}
