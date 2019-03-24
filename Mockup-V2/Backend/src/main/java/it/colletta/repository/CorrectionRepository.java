package it.colletta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import it.colletta.model.CorrectionModel;
import it.colletta.repository.CorrectionCustomQueryInterface;

@Repository
public interface CorrectionRepository
        extends MongoRepository<CorrectionModel, String>, CorrectionCustomQueryInterface {

}
