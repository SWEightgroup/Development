package it.colletta.service.signup;

import it.colletta.model.SignupRequestModel;
import it.colletta.repository.administration.SingupRequestRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;


public class SignupRequestService {

    @Autowired
    private SingupRequestRepository singupRequestRepository;

    public SignupRequestModel addSignUpRequest(@NonNull SignupRequestModel requestModel) {
        return singupRequestRepository.save(requestModel);
    }
}
