package tr11.theater.dto.contract;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContractRequestDto {
    @NotNull
    @Min(value = 1)
    private Long actorId;
    @NotNull
    @Min(value = 0)
    private Long performanceId;
    @NotNull
    @Size(min = 5, max = 254)
    private String role;
    @NotNull
    @Min(value = 0)
    private BigDecimal salary;
}
