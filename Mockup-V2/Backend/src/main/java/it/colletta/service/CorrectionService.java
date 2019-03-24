package it.colletta.service;

import it.colletta.library.FreelingAdapterSocket;
import it.colletta.repository.CorrectionRepository;
import it.colletta.library.FreelingAdapterInterface;
import it.colletta.model.Correction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CorrectionService {
  private final String host = "18.197.54.200";
  private final int port = 50005;
  @Autowired
  private CorrectionRepository correctionRepository;
  // TODO
  public Correction findCorrection(String Id) {
    return new Correction();
  }

  public Correction getAutomaticCorrection(String correctionText) throws IOException  {
    FreelingAdapterInterface freelingLibrary = new FreelingAdapterSocket(host, port);
    Correction c = new Correction();
    freelingLibrary.getCorrection(correctionText);
    freelingLibrary.closeConnection();
    return c;
  }
}
