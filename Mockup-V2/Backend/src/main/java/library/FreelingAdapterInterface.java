package library;

import java.io.IOException;

public interface FreelingAdapterInterface {
  
  /**
   * get a sentence correction.
   * 
   * @param sentence String.
   * @return String correction.
   */
  String getCorrection(String sentence);

  /**
   * Close the connection
   * @throws IOException.
   */
  void closeConnection() throws IOException;
}
