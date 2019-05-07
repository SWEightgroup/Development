package it.colletta.controller.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import it.colletta.controller.UserController;
import it.colletta.controller.assembler.resource.UserResource;
import it.colletta.model.UserModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserResourceAssembler extends
        ResourceAssemblerSupport<UserModel, UserResource> {

  private String linkString;

  private UserResourceAssembler() {
    super(UserController.class, UserResource.class);
  }

  public UserResourceAssembler(String linkString) {
    this();
    this.linkString = linkString;
  }

  /**
   * Create a new resource for links
   *
   * @param user the user we want link
   * @return the resource for pagination
   */

  public UserResource createResource(UserModel user) {
    UserResource personResource = new UserResource(user);
    Link link = linkTo(UserController.class).slash(linkString).withSelfRel();
    personResource.add(link);
    return personResource;
  }

  @Override
  public UserResource toResource(UserModel user) {
    UserResource resource = createResource(user);
    return resource;
  }
}
