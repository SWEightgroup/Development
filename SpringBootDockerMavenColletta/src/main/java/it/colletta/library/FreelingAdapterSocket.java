package it.colletta.library;

import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * FreelingAdapterSocket.
 * 
 * @author Francesco
 *
 */
public class FreelingAdapterSocket implements FreelingAdapterInterface {

  private FreelingSocketClient socketClient;

  /**
   * FreelingAdapterSocket constructor.
   * 
   * @param host server address 
   * @param port server port
   */
  public FreelingAdapterSocket(String host, int port) {
    if (StringUtils.countOccurrencesOf(host, ".") != 3) {
      throw new IllegalArgumentException("L'host inserito non e' nel formato corretto");
    } else {
      socketClient = new FreelingSocketClient(host, port);
    }
  }

  /**
   * getCorrection method.
   * 
   * @param sentence to analyze
   */
  public String getCorrection(String sentence) {
    String analyzedSentence = "";
    if (sentence.length() == 0) {
      throw new IllegalArgumentException("La stringa non può essere vuota");
    }
    if (StringUtils.countOccurrencesOf(sentence, ".") == 0) {
      sentence = sentence + "."; // questa parte sarà da valutare
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
   * Closes the connection to the server.
   */
  public void closeConnection() throws IOException {
    socketClient.close();
  }
}
