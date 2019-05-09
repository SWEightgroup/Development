package it.colletta.library;

import java.io.IOException;

public interface FreelingAdapterInterface {

  /**
   * get a sentence correction.
   *
   * @param sentence String.
   * @return String correction.
   */
  String getCorrection(final String sentence);

  /**
   * Close the connection.
   *
   * @throws IOException Exception.
   */

  void closeConnection() throws IOException;
}
