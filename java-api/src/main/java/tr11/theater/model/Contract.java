package tr11.theater.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;
    @OneToOne
    @JoinColumn(name = "performance_id")
    private Performance performance;
    private String role;
    private Double salary;
}
