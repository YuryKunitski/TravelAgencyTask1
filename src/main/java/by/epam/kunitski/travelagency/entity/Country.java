package by.epam.kunitski.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = "tourList")
@EqualsAndHashCode(exclude = "tourList")
@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "country_id")
    private Set<Tour> tourList = new HashSet<>();

}
