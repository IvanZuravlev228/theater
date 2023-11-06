package tr11.theater.dto.contract;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ContractResponseDto {
    private Long id;
    private Long actorId;
    private Long performanceId;
    private String role;
    private BigDecimal salary;
}
