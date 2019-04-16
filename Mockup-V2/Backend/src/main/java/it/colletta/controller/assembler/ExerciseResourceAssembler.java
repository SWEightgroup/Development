package it.colletta.controller.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import it.colletta.controller.ExerciseController;
import it.colletta.controller.assembler.resource.ExerciseResource;
import it.colletta.model.ExerciseModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class ExerciseResourceAssembler extends
    ResourceAssemblerSupport<ExerciseModel, ExerciseResource> {

  private String linkString;

  private ExerciseResourceAssembler() {
    super(ExerciseController.class, ExerciseResource.class);
  }

  public ExerciseResourceAssembler(String linkString) {
    this();
    this.linkString = linkString;
  }

  public ExerciseResource createResource(ExerciseModel exercise) {
    ExerciseResource personResource = new ExerciseResource(exercise);
    Link link = linkTo(ExerciseController.class).slash(linkString).withSelfRel();
    personResource.add(link);
    return personResource;
  }

  @Override
  public ExerciseResource toResource(ExerciseModel exercise) {
    ExerciseResource resource = createResource(exercise);
    return resource;
  }
}