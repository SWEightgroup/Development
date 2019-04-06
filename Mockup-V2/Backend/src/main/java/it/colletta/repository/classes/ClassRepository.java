package it.colletta.repository.classes;

import it.colletta.model.ClassModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository
    extends MongoRepository<ClassModel, String>, ClassCustomQueryInterface {}
