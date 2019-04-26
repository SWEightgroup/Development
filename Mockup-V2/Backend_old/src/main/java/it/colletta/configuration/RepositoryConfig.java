package it.colletta.configuration;

import it.colletta.model.ExerciseModel;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(ExerciseModel.class);
    config.exposeIdsFor(SolutionModel.class);
    config.exposeIdsFor(PhraseModel.class);
    config.exposeIdsFor(UserModel.class);
  }
}