package it.colletta.controller.assembler.resource;

import it.colletta.model.ExerciseModel;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ExerciseResource extends Resource<ExerciseModel> {

  public ExerciseResource(ExerciseModel content, Link... links) {
    super(content, links);
  }
}
