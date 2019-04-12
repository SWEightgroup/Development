package it.colletta.service;

import it.colletta.library.FreelingAdapterInterface;
import it.colletta.library.FreelingAdapterSocket;
import it.colletta.model.SolutionModel;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class SolutionService {

  // TODO Move to a static class
  private final String host = "18.197.54.200";
  private final int port = 50005;

  // TODO
  public SolutionModel findSolution(String Id) {
    return new SolutionModel();
  }

  /** @return SolutionModel */
  public SolutionModel getAutomaticCorrection(String correctionText) throws IOException {
    FreelingAdapterInterface freelingLibrary = new FreelingAdapterSocket(host, port);
    SolutionModel solutionModel =
        SolutionModel.builder()
            .solutionText(freelingLibrary.getCorrection(correctionText).trim())
            .reliability(0)
            .dateSolution(System.currentTimeMillis())
            .build();
    freelingLibrary.closeConnection();
    return solutionModel;
  }
}
