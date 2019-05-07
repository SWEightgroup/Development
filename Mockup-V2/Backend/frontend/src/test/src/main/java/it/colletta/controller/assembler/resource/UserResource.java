package it.colletta.controller.assembler.resource;

import it.colletta.model.UserModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class UserResource extends Resource<UserModel> {

  public UserResource(UserModel content, Link... links) {
    super(content, links);
  }

}