package by.epam.kunitski.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = {"reviewList", "tourList"})
@EqualsAndHashCode(exclude = {"reviewList", "tourList"})
@Entity
@Table(name = "public.\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;

    @OneToMany(mappedBy = "userID", cascade=CascadeType.ALL)
    private Set<Review> reviewList = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_tour", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="tour_id") )
    private Set<Tour> tourList = new HashSet<>();

}
