package tr11.theater.dto.performance;

import java.util.List;
import lombok.Data;

@Data
public class PerformancesResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double budget;
    private List<Long> actorIds;
}
