package by.epam.kunitski.travelagency.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = {"reviewList", "userList"})
@EqualsAndHashCode(exclude = {"reviewList", "userList"})
@Entity
@Table(name = "tour")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String photo;

    @NotNull
    @FutureOrPresent
    private LocalDate date;

    @Range(min = 1, max = 99)
    private int duration;

    @Size(min = 1, max = 500)
    private String description;

    @Positive
    private Double cost;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "hotel_id")
    private Hotel hotel_id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "country_id")
    private Country country_id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum" )
    private TourType tour_type;

    @OneToMany(mappedBy = "tourID", cascade = CascadeType.ALL)
    private Set<Review> reviewList = new HashSet<>();

    @ManyToMany(mappedBy="tourList")
    private Set<User> userList = new HashSet<>();

   public enum TourType {
        ECONOM, ALL_INCLUSIVE, ONLY_BREAKFAST
    }

}
