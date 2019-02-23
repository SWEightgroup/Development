package library;
import java.io.IOException;

public interface FreelingAdapterInterface {
    String getCorrection(String sentence);
    void closeConnection() throws IOException;
}