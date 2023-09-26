package tr11.theater.dto.actor;

import java.util.List;
import lombok.Data;

@Data
public class ActorRequestDto {
    private String name;
    private String lastname;
    private Double experience;
    private List<Long> prizeIds;
}
