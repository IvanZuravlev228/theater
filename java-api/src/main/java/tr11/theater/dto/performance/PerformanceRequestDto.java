package tr11.theater.dto.performance;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PerformanceRequestDto {
    @NotNull
    @Size(min = 3, max = 30)
    private String name;
    @NotNull
    @Size(min = 3, max = 700)
    private String description;
    @NotNull
    @Min(value = 0)
    private Double budget;
    @NotNull
    private List<Long> actorIds;
}
