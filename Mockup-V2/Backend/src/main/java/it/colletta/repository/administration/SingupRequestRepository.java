package it.colletta.repository.administration;

import it.colletta.model.SignupRequestModel;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SingupRequestRepository extends MongoRepository<SignupRequestModel, String> {
}
