package tr11.theater.service;

import tr11.theater.model.Prizes;

import java.util.List;

public interface PrizesService {
    Prizes save(Prizes prizes);

    Prizes getById(Long id);

    List<Prizes> getAll();

    Prizes update(Long previousId, Prizes forUpdate);

    void delete(Long id);
}
