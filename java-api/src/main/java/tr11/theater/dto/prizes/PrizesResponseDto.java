package tr11.theater.dto.prizes;

import lombok.Data;

@Data
public class PrizesResponseDto {
    private Long id;
    private String name;
    private Double coefficient;
}
