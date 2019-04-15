/**
 * @path it.colletta.controller.ExerciseController
 * @author Francesco Magarotto, Enrico Muraro, Francesco Corti
 * @date 2019-03-27
 * @description Menage the HTTP user request regarding the exercises
 */
package it.colletta.library;

import org.springframework.util.StringUtils;

import java.io.IOException;


public class FreelingAdapterSocket implements FreelingAdapterInterface {

  private FreelingSocketClient socketClient;

  /**
   * Creates a socket.
   *
   * @param host address
   * @param port address
   */
  public FreelingAdapterSocket(final String host, int port) {
    if (StringUtils.countOccurrencesOf(host, ".") != 3) {
      throw new IllegalArgumentException("L'host " + "inserito non e' nel formato corretto");
    } else {
      socketClient = new FreelingSocketClient(host, port);
    }
  }

  /**
   * get a sentence's correction
   *
   * @param sentence String.
   * @return correction String.
   */
  public String getCorrection(String sentence) {
    String analyzedSentence = "";
    if (sentence == null || sentence.length() == 0) {
      throw new IllegalArgumentException("La stringa non può essere vuota");
    }
    if (StringUtils.countOccurrencesOf(sentence, ".") == 0) {
      sentence = sentence + " ."; // questa parte sarà da valutare
    }
    try {
      analyzedSentence = socketClient.processSegment(sentence);
      if (analyzedSentence.equals(socketClient.getReadyMsg())) {
        throw new IOException("Il socket non e' pronto");
      }
    } catch (IOException error) {
      error.printStackTrace();
    }
    return analyzedSentence;
  }

  /**
   * Close the connection.
   *
   * @throws IOException IOException.
   */
  public void closeConnection() throws IOException {
    socketClient.close();
  }
}
