package it.colletta.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import it.colletta.model.SolutionModel;
import it.colletta.repository.SolutionCustomQueryInterface;

@Repository
public interface SolutionRepository
        extends MongoRepository<SolutionModel, String>, SolutionCustomQueryInterface {

        @Query(value = "{'solutionText': {$regex: ?0, $options: 'i'}}")
        public List<SolutionModel> getSolutionWithText(String textToCompare);

        public Long increaseAffidability(SolutionModel solution);

}
