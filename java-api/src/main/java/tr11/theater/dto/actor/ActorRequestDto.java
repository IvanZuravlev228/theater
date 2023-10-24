package tr11.theater.dto.actor;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActorRequestDto {
    @NotNull
    @Size(min = 4, max = 30)
    private String name;
    @NotNull
    @Size(min = 4, max = 30)
    private String lastname;
    @NotNull
    @Min(value = 0)
    private Double experience;
    @NotNull
    private List<Long> prizeIds;
}
