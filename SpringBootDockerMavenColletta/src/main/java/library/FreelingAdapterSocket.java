package library;
import java.io.IOException;

import org.springframework.util.StringUtils;

/**
 * 
 * @author gioleg
 *
 */
public class FreelingAdapterSocket implements FreelingAdapterInterface {
	
    private FreelingSocketClient socketClient;
    
    /**
     * 
     * @param host
     * @param port
     */
    public FreelingAdapterSocket(String host, int port) {
        if(StringUtils.countOccurrencesOf(host, ".") != 3) {
            throw new IllegalArgumentException("L'host inserito non e' nel formato corretto");
        }
        else {
            socketClient = new FreelingSocketClient(host, port);
        }
    }
    
    
    /**
     * 
     */
    public String getCorrection(String sentence) {
        String analyzedSentence = new String();
        if(sentence.length() == 0) {
            throw new IllegalArgumentException("La stringa non può essere vuota");
        }
        if(StringUtils.countOccurrencesOf(sentence, ".") == 0) {
            sentence = sentence + "."; // questa parte sarà da valutare
        }
        try {
           analyzedSentence = socketClient.processSegment(sentence);
           if(analyzedSentence.equals(socketClient.getReadyMSG())) {
               throw new IOException("Il socket non e' pronto");
           }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return analyzedSentence;
    }
    
    /**
     * 
     */
    public void closeConnection()  throws IOException {
        socketClient.close();
    }
}