package by.epam.kunitski.travelagency.dao.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = "tourList")
@EqualsAndHashCode(exclude = "tourList")
@Entity
@Table(name = "hotel")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @Range(min = 1, max = 5)
    private int stars;

    @URL
    private String website;

    @Range(min = -90, max = 90)
    private double latitude;

    @Range(min = -180, max = 180)
    private double longitude;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private FeatureType features;

    @OneToMany(mappedBy = "hotel_id", cascade = CascadeType.ALL)
    private Set<Tour> tourList = new HashSet<>();

    public enum FeatureType {
        SWIMMING_POOL, FREE_WIFI, PARKING, CHILDREN_AREA, RESTAURANT
    }

}
