package it.colletta.repository.administration;

import it.colletta.model.ForgotPasswordModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepository extends MongoRepository<ForgotPasswordModel, String> {
}
