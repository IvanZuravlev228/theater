package tr11.theater.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Objects;

@Data
@SQLDelete(sql = "UPDATE actors SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
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
    private boolean isDeleted = false;
}
