package tr11.theater.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr11.theater.model.Prizes;
import tr11.theater.repository.PrizesRepository;
import tr11.theater.service.PrizesService;

@Service
@RequiredArgsConstructor
public class PrizesServiceImpl implements PrizesService {
    private final PrizesRepository prizesRepository;

    @Override
    public Prizes save(Prizes prizes) {
        return prizesRepository.save(prizes);
    }

    @Override
    public Prizes getById(Long id) {
        return prizesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find prizes by id: " + id));
    }

    @Override
    public List<Prizes> getAll() {
        return prizesRepository.findAll();
    }

    @Override
    public Prizes update(Long previousId, Prizes forUpdate) {
        Prizes prevPrize = getById(previousId);
        forUpdate.setId(prevPrize.getId());
        return prizesRepository.save(forUpdate);
    }

    @Override
    public void delete(Long id) {
        prizesRepository.deleteById(id);
    }

    @Override
    public List<Prizes> getAllByIds(List<Long> ids) {
        return ids.stream().map(this::getById).collect(Collectors.toList());
    }
}
