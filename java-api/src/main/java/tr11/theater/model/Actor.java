package tr11.theater.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "lastname")
    private String lastname;
    private Double experience;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "prizes_actors",
            joinColumns = @JoinColumn(name = "actors_id"),
            inverseJoinColumns = @JoinColumn(name = "prizes_id"))
    private List<Prizes> prizes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id) && Objects.equals(name, actor.name) && Objects.equals(lastname, actor.lastname) && Objects.equals(experience, actor.experience) && Objects.equals(prizes, actor.prizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, experience, prizes);
    }
}
