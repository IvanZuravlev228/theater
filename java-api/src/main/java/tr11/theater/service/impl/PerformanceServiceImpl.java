package tr11.theater.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr11.theater.exception.NotFoundException;
import tr11.theater.model.Actor;
import tr11.theater.model.Performance;
import tr11.theater.repository.PerformanceRepository;
import tr11.theater.service.ActorService;
import tr11.theater.service.PerformanceService;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final ActorService actorService;

    @Override
    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    @Override
    public Performance getById(Long id) {
        return performanceRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Can't find performance by id: " + id));
    }

    @Override
    public List<Performance> getAll(Pageable pageable) {
        return performanceRepository.findAll(pageable).stream().collect(Collectors.toList());
    }

    @Override
    public Performance addActorToPerformance(Long actorId, Long performanceId) {
        Actor actor = actorService.getById(actorId);
        Performance performance = getById(performanceId);
        performance.getActors().add(actor);
        return performanceRepository.save(performance);
    }

    @Override
    public Performance deleteActorFromPerformance(Long actorId, Long performanceId) {
        Actor actor = actorService.getById(actorId);
        Performance performance = getById(performanceId);
        performance.getActors().remove(actor);
        return performanceRepository.save(performance);
    }

    @Override
    public Performance update(Long prevId, Performance newPerformance) {
        Performance prevPerformance = getById(prevId);
        newPerformance.setId(prevPerformance.getId());
        return performanceRepository.save(newPerformance);
    }

    @Override
    public void delete(Long id) {
        performanceRepository.deleteById(id);
    }
}
