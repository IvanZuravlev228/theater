package tr11.theater.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@SQLDelete(sql = "UPDATE performances SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
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
    private boolean isDeleted = false;
}
