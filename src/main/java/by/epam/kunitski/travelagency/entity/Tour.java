package by.epam.kunitski.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = {"reviewList", "userList"})
@EqualsAndHashCode(exclude = {"reviewList", "userList"})
@Entity
@Table(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String photo;
    private LocalDate date;
    private int duration;
    private String description;
    private Double cost;

    @ManyToOne()//fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    @OnDelete(action = OnDeleteAction.CASCADE )
    private Hotel hotel_id;

    @ManyToOne()//fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @OnDelete(action = OnDeleteAction.CASCADE )
    private Country country_id;

    @Enumerated(EnumType.STRING)
    private TourType tour_type;

    @OneToMany(mappedBy = "tourID")//, cascade = CascadeType.ALL)
    private Set<Review> reviewList = new HashSet<>();

    @ManyToMany(mappedBy="tourList", fetch = FetchType.LAZY)
    private Set<User> userList = new HashSet<>();

   public enum TourType {
        ECONOM, ALL_INCLUSIVE, ONLY_BREAKFAST
    }

}
