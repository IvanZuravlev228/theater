package tr11.theater.model;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@SQLDelete(sql = "UPDATE actors SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
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
    private BigDecimal salary;
    private boolean isDeleted = false;
}
