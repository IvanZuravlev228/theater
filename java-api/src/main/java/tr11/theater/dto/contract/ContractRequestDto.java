package tr11.theater.dto.contract;

import lombok.Data;

@Data
public class ContractRequestDto {
    private Long actorId;
    private Long performanceId;
    private String role;
    private Double salary;
}
