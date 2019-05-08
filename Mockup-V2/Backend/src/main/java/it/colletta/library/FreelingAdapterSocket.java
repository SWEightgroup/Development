package it.colletta.library;

import java.io.IOException;
import org.springframework.util.StringUtils;


public class FreelingAdapterSocket implements FreelingAdapterInterface {

  private FreelingSocketClient socketClient;

  /**
   * Creates a socket.
   *
   * @param host address.
   * @param port address.
   */
  public FreelingAdapterSocket(final String host, int port) {
    socketClient = new FreelingSocketClient(host, port);
  }

  /**
   * get a sentence's correction.
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
