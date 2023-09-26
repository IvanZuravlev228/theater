package tr11.theater.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double budget;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "performances_actors",
            joinColumns = @JoinColumn(name = "performances_id"),
            inverseJoinColumns = @JoinColumn(name = "actors_id"))
    private List<Actor> actors;
}
