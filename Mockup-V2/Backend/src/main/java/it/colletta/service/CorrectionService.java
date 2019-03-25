package it.colletta.service;

import it.colletta.library.FreelingAdapterSocket;
import it.colletta.repository.CorrectionRepository;
import it.colletta.library.FreelingAdapterInterface;
import it.colletta.model.CorrectionModel;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CorrectionService {
  //TODO Move in a static class
  private final String host = "18.197.54.200";
  private final int port = 50005;
  @Autowired
  private CorrectionRepository correctionRepository;
  // TODO
  public CorrectionModel findCorrection(String Id) {
    return new CorrectionModel();
  }

  public CorrectionModel getAutomaticCorrection(String correctionText) throws IOException  {
    FreelingAdapterInterface freelingLibrary = new FreelingAdapterSocket(host, port);
    CorrectionModel c = new CorrectionModel();
    c.setAffidability(0.0);
    c.setCorrectionText(freelingLibrary.getCorrection(correctionText));
    freelingLibrary.closeConnection();
    return c;
  }
}
