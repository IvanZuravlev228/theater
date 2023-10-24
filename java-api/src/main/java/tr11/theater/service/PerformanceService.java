package tr11.theater.service;

import org.springframework.data.domain.Pageable;
import tr11.theater.model.Performance;

import java.util.List;

public interface PerformanceService {
    Performance save(Performance performance);

    Performance getById(Long id);

    List<Performance> getAll(Pageable pageable);

    Performance addActorToPerformance(Long actorId, Long performanceId);

    Performance deleteActorFromPerformance(Long actorId, Long performanceId);

    Performance update(Long prevId, Performance newPerformance);

    void delete(Long id);
}
