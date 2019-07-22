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
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue
    private int id;
    private LocalDate date;
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private int userID;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private int tourID;
}
