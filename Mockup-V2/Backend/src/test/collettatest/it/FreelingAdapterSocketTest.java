package collettatest.it;
import it.colletta.library.FreelingAdapterSocket;
import org.junit.Test;
import static org.junit.Assert.*;

public class FreelingAdapterSocketTest {
    private final String host = "18.197.54.200";
    private final int port = 50005;
    FreelingAdapterSocket freelingAdapterSocket = new FreelingAdapterSocket(host, port);
    @Test(expected = IllegalArgumentException.class)
    public void getCorrectionWithEmptyString() {
        String text = "";
        freelingAdapterSocket.getCorrection(text);
    }

    @Test
    public void getAnalyzedSentence() {
        String text = "Ciao come stai?";
        String solution = "{ \"sentences\" : [\n" +
                "      { \"id\":\"1\",\n" +
                "        \"tokens\" : [\n" +
                "           { \"id\" : \"t1.1\", \"begin\" : \"0\", \"end\" : \"4\", \"form\" : \"Ciao\", \"lemma\" : \"ciao\", \"tag\" : \"NP00000\", \"ctag\" : \"NP\", \"pos\" : \"noun\", \"type\" : \"proper\"},\n" +
                "           { \"id\" : \"t1.2\", \"begin\" : \"5\", \"end\" : \"9\", \"form\" : \"come\", \"lemma\" : \"come\", \"tag\" : \"PT00000\", \"ctag\" : \"PT\", \"pos\" : \"pronoun\", \"type\" : \"interrogative\"},\n" +
                "           { \"id\" : \"t1.3\", \"begin\" : \"10\", \"end\" : \"14\", \"form\" : \"stai\", \"lemma\" : \"stare\", \"tag\" : \"VMIP2S0\", \"ctag\" : \"VMI\", \"pos\" : \"verb\", \"type\" : \"main\", \"mood\" : \"indicative\", \"tense\" : \"present\", \"person\" : \"2\", \"num\" : \"singular\"},\n" +
                "           { \"id\" : \"t1.4\", \"begin\" : \"14\", \"end\" : \"15\", \"form\" : \"?\", \"lemma\" : \"?\", \"tag\" : \"Fit\", \"ctag\" : \"Fit\", \"pos\" : \"punctuation\", \"type\" : \"questionmark\", \"punctenclose\" : \"close\"},\n" +
                "           { \"id\" : \"t1.5\", \"begin\" : \"16\", \"end\" : \"17\", \"form\" : \".\", \"lemma\" : \".\", \"tag\" : \"Fp\", \"ctag\" : \"Fp\", \"pos\" : \"punctuation\", \"type\" : \"period\"}]}]}\n";
        assertEquals(freelingAdapterSocket.getCorrection(text).trim(), solution.trim());
    }
}