package it.colletta.service;

import it.colletta.library.FreelingAdapterSocket;
import it.colletta.repository.SolutionRepository;
import it.colletta.library.FreelingAdapterInterface;
import it.colletta.model.SolutionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SolutionService {
  //TODO Move in a static class
  private final String host = "18.197.54.200";
  private final int port = 50005;
  @Autowired
  private SolutionRepository solutionRepository;
  
  // TODO
  public SolutionModel findSolution(String Id) {
    return new SolutionModel();
  }

  public SolutionModel getAutomaticCorrection(String correctionText) throws IOException  {
    FreelingAdapterInterface freelingLibrary = new FreelingAdapterSocket(host, port);
    SolutionModel c = new SolutionModel();
    c.setAffidability(0.0);
    c.setSolutionText(freelingLibrary.getCorrection(correctionText));
    freelingLibrary.closeConnection();
    return c;
  }
}
