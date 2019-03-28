package it.colletta.service;

import it.colletta.library.FreelingAdapterSocket;
import it.colletta.repository.solution.SolutionRepository;
import it.colletta.library.FreelingAdapterInterface;
import it.colletta.model.SolutionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;

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

  /**
  * @param correctionText
  * @throws IOException
  * @return SolutionModel
  */
  public SolutionModel getAutomaticCorrection(String correctionText) throws IOException  {
    FreelingAdapterInterface freelingLibrary = new FreelingAdapterSocket(host, port);
    SolutionModel solutionModel = SolutionModel.builder()
            .solutionText(correctionText)
            .affidability(0)
            .dateSolution(System.currentTimeMillis())
            .build();
    freelingLibrary.closeConnection();
    return solutionModel;
  }
  
  /**
  * @param solution :the solution of the phrase referenced by the phraseId 
  */
  public SolutionModel insertSolution(SolutionModel solution) {
    if(solution != null) {
          solutionRepository.increaseAffidability(solution);
          return solutionRepository.save(solution);
      }
      else {   // insert the new solution 
         /*PhraseModel actualPhrase = PhraseService.getById(phraseId);    // get the phraseModel who is going to have the subcollect solution
         actualPhrase.addSolution()
         solutionRepository.c
         SolutionModel solutionToInsert.builder;*/
         
         /**
          * add a solutionText
            set dateSolution
            set affidability = 0
            set authorId
          */
      }
    }

}
