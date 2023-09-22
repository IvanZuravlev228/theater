package tr11.theater.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "prizes")
public class Prizes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double coefficient;
}
