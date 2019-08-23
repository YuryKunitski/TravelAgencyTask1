package by.epam.kunitski.travelagency.dao.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"reviewList", "tourList"})
@EqualsAndHashCode(exclude = {"reviewList", "tourList"})
@Entity
@Table(name = "public.\"user\"")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, max = 20)
    private String login;

    @Size(min = 3, max = 20)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum" )
    private UserRole role;

    @OneToMany(mappedBy = "userID", cascade=CascadeType.ALL)
    private Set<Review> reviewList = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_tour", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="tour_id") )
    private Set<Tour> tourList = new HashSet<>();

    public enum UserRole {
        MEMBER, ADMIN
    }

}
