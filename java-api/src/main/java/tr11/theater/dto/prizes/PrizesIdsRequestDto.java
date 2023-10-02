package tr11.theater.dto.prizes;

import java.util.List;
import lombok.Data;

@Data
public class PrizesIdsRequestDto {
    private List<Long> prizesIds;
}
