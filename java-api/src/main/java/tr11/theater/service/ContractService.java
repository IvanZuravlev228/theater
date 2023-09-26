package tr11.theater.service;

import tr11.theater.model.Contract;

import java.util.List;

public interface ContractService {
    Contract save(Contract contract);

    Contract getById(Long id);

    Contract getByActorId(Long actorId);

    List<Contract> getAll();

    Contract update(Long previousId, Contract contract);

    void delete(Long id);
}
