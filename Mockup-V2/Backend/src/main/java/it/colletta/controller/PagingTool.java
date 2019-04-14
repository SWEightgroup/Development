package it.colletta.controller;

import it.colletta.model.ExerciseModel;
import org.springframework.hateoas.PagedResources;

public class PagingTool<T> {

  public String createLinkHeader(PagedResources<T> pr) {
    final StringBuilder linkHeader = new StringBuilder();
    if(!pr.getLinks("first").isEmpty()) {
      linkHeader.append(buildLinkHeader(pr.getLinks("first").get(0).getHref(), "first"));
      linkHeader.append(", ");
    }
    if(!pr.getLinks("next").isEmpty()) {
      linkHeader.append(buildLinkHeader(pr.getLinks("next").get(0).getHref(), "next"));
    }
    return linkHeader.toString();
  }

  public static String buildLinkHeader(final String uri, final String rel) {
    return "<" + uri + ">; rel=\"" + rel + "\"";
  }

}
