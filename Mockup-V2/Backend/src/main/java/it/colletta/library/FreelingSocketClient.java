package it.colletta.library;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * A simple client to communicate with Freeling 3.1 server
 */
public class FreelingSocketClient {

  private static final String server_ready_msg = "FL-SERVER-READY";
  private static final String reset_stat_msg = "RESET_STATS";
  private static final String encoding = "UTF8";
  private static final String flush_buffer_msg = "FLUSH_BUFFER";
  private static final int buf_size = 2048;

  Socket socket;
  DataInputStream bufferEntrada;
  DataOutputStream bufferSalida;


  /**
   * Creates a socket.
   *
   * @param host address.
   * @param port address.
   */
  public FreelingSocketClient(final String host, int port) {
    try {

      socket = new Socket(host, port);
      socket.setSoLinger(true, 10);
      socket.setKeepAlive(true);
      socket.setSoTimeout(10000);
      bufferEntrada = new DataInputStream(socket.getInputStream());
      bufferSalida = new DataOutputStream(socket.getOutputStream());
      writeMessage(bufferSalida, reset_stat_msg, encoding);

      StringBuffer sb = readMessage(bufferEntrada);
      if (sb.toString().trim().compareTo(server_ready_msg) != 0) {
        System.err.println("SERVER NOT READY!");
      }
      writeMessage(bufferSalida, flush_buffer_msg, encoding);
      readMessage(bufferEntrada);

    } catch (Exception error) {
      error.printStackTrace();
    }
  }

  /**
   * WriteMessage.
   *
   * @param out out.
   * @param message message.
   * @param encoding encoding.
   * @throws IOException Exception.
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
   * @param bufferEntrada bufferEntrada.
   * @return StringBuffer StringBufferì.
   * @throws IOException Exception.
   */
  private static synchronized StringBuffer readMessage(DataInputStream bufferEntrada)
      throws IOException {

    byte[] buffer = new byte[buf_size];
    int bl = 0;
    StringBuffer sb = new StringBuffer();

    // messages ends with
    do {
      bl = bufferEntrada.read(buffer, 0, buf_size);
      if (bl > 0) {
        sb.append(new String(buffer, 0, bl));
      }
    } while (bl > 0 && buffer[bl - 1] != 0);
    return sb;
  }

  /**
   * processSegment.
   *
   * @param text text.
   * @return String String.
   * @throws IOException Exception.
   */
  public String processSegment(final String text) throws IOException {
    writeMessage(bufferSalida, text, encoding);
    StringBuffer sb = readMessage(bufferEntrada);
    writeMessage(bufferSalida, flush_buffer_msg, encoding);
    readMessage(bufferEntrada);
    return sb.toString();
  }

  /**
   * Close the connection.
   *
   * @throws IOException Exception.
   */
  public void close() throws IOException {
    socket.close();
  }

  /**
   * getReadyMSG.
   *
   * @return String String.
   */
  public String getReadyMsg() {
    return server_ready_msg;
  }
}
