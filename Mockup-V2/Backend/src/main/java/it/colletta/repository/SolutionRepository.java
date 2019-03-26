package it.colletta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import it.colletta.model.SolutionModel;;
import it.colletta.repository.SolutionCustomQueryInterface;

@Repository
public interface SolutionRepository
        extends MongoRepository<SolutionModel, String>, SolutionCustomQueryInterface {

}
