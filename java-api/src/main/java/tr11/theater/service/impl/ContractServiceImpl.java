package tr11.theater.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr11.theater.exception.NotFoundException;
import tr11.theater.model.Contract;
import tr11.theater.repository.ContractRepository;
import tr11.theater.service.ContractService;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract getById(Long id) {
        return contractRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Can't find actor by id: " + id));
    }

    @Override
    public Contract getByActorAndPerformanceId(Long actorId, Long perfrormanceId) {
        return contractRepository.findByActorAndPerformanceId(actorId, perfrormanceId).orElseThrow(() ->
                new NotFoundException("Can't find actor by actor's id: " + actorId));
    }

    @Override
    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract update(Long previousId, Contract contract) {
        Contract prevContract = getById(previousId);
        contract.setId(prevContract.getId());
        return contractRepository.save(contract);
    }

    @Override
    public void delete(Long id) {
        contractRepository.deleteById(id);
    }
}
