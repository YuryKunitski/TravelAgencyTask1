package by.epam.kunitski.travelagency.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private String text;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User userID;

    @ManyToOne()
    @JoinColumn(name = "tour_id")
    private Tour tourID;
}
