package it.colletta.service.signup;

import it.colletta.model.SignupRequestModel;
import it.colletta.repository.administration.SingupRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.NonNull;

public class SignupRequestService {

  @Autowired
  private SingupRequestRepository singupRequestRepository;

  /**
   * 
   * @param requestModel
   * @return
   */
  public SignupRequestModel addSignUpRequest(@NonNull SignupRequestModel requestModel) {
    return singupRequestRepository.save(requestModel);
  }
}
