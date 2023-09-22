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
    private Actor actor;
    private Double salary;
}
