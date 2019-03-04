package it.colletta.library;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * A simple client to communicate with Freeling 3.1 server.
 * @author Gionata
 *
 */
public class FreelingSocketClient {
  private static final String SERVER_READY_MSG = "FL-SERVER-READY";
  private static final String RESET_STATS_MSG = "RESET_STATS";
  private static final String ENCODING = "UTF8";
  private static final String FLUSH_BUFFER_MSG = "FLUSH_BUFFER";
  private static final int BUF_SIZE = 2048;

  Socket socket;
  DataInputStream bufferEntrada;
  DataOutputStream bufferSalida;

  /**
   * FreelingSocketClient constructor.
   * 
   * @param host server address
   * @param port server port
   */
  public FreelingSocketClient(String host, long port) {
    try {

      socket = new Socket(host, 50005);
      socket.setSoLinger(true, 10);
      socket.setKeepAlive(true);
      socket.setSoTimeout(10000);
      bufferEntrada = new DataInputStream(socket.getInputStream());
      bufferSalida = new DataOutputStream(socket.getOutputStream());
      writeMessage(bufferSalida, RESET_STATS_MSG, ENCODING);

      StringBuffer sb = readMessage(bufferEntrada);
      if (sb.toString().compareTo(SERVER_READY_MSG) != 0) {
        System.err.println("SERVER NOT READY!");
      }
      writeMessage(bufferSalida, FLUSH_BUFFER_MSG, ENCODING);
      readMessage(bufferEntrada);

    } catch (Exception error) {
      error.printStackTrace();
    }
  }

  /**
   * writeMessage.
   * 
   * @param out out
   * @param message message
   * @param encoding encoding
   * @throws IOException IOException
   */
  public static void writeMessage(java.io.DataOutputStream out, String message, String encoding)
      throws IOException {
    out.write(message.getBytes(encoding));
    out.write(0);
    out.flush();
  }

  /**
   * readMessage.
   * 
   * @param bufferEntrada bufferEntrada
   * @return StringBuffer
   * @throws IOException IOException
   */
  private static synchronized StringBuffer readMessage(DataInputStream bufferEntrada)
      throws IOException {

    byte[] buffer = new byte[BUF_SIZE];
    int bl = 0;
    StringBuffer sb = new StringBuffer();

    // messages ends with
    do {
      bl = bufferEntrada.read(buffer, 0, BUF_SIZE);
      if (bl > 0) {
        sb.append(new String(buffer, 0, bl));
      }
    } while (bl > 0 && buffer[bl - 1] != 0);
    return sb;
  }

  /**
   * processSegment.
   * 
   * @param text text
   * @return solution
   * @throws IOException IOException
   */
  public String processSegment(String text) throws IOException {
    writeMessage(bufferSalida, text, ENCODING);
    StringBuffer sb = readMessage(bufferEntrada);
    writeMessage(bufferSalida, FLUSH_BUFFER_MSG, ENCODING);
    readMessage(bufferEntrada);
    return sb.toString();
  }

  /**
   * close connection.
   * 
   * @throws IOException IOException
   */
  public void close() throws IOException {
    socket.close();
  }

  /**
   * getReadyMSG.
   * 
   * @return String
   */
  public String getReadyMsg() {
    return SERVER_READY_MSG;
  }
}
