package tr11.theater.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;

public interface DefaultCrudController<RQ, RS> {
    ResponseEntity<RS> save(RQ requestEntity);

    ResponseEntity<RS> getById(Long id);

    ResponseEntity<List<RS>> getAll();

    ResponseEntity<RS> update(Long prevId, RQ newEntity);

    ResponseEntity<Void> deleteById(Long id);
}
