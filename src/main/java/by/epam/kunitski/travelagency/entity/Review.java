package by.epam.kunitski.travelagency.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @PastOrPresent
    private LocalDate date;

    @Size(min = 1, max = 500)
    private String text;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User userID;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "tour_id")
    private Tour tourID;
}
