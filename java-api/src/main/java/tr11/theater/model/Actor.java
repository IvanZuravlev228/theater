package tr11.theater.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
}
