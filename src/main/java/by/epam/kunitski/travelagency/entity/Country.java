package by.epam.kunitski.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Country name cannot be null")
    private String name;


    @OneToMany(mappedBy = "country_id", cascade=CascadeType.ALL)
    private Set<Tour> tourList = new HashSet<>();

}
