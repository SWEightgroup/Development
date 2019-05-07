package it.colletta.service;


import it.colletta.model.SolutionModel;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SolutionServiceTest {

    private SolutionService solutionService;

    private String mysolution;

    @Before
    public void setUp() {

        solutionService = new SolutionService();

        mysolution = new String("{ \"sentences\" : [\n" + "{ \"id\":\"1\",\n" +
                "\"tokens\" : [\n" +
                "{ \"id\" : \"t1.1\", \"begin\" : \"0\", \"end\" : \"4\", \"form\" : \"sono\", \"lemma\" : \"essere\", \"tag\" : \"VMIP1S0\", \"ctag\" : \"VMI\", \"pos\" : \"verb\", \"type\" : \"main\", \"mood\" : \"indicative\", \"tense\" : \"present\", \"person\" : \"1\", \"num\" : \"singular\"},\n" +
                " { \"id\" : \"t1.2\", \"begin\" : \"5\", \"end\" : \"7\", \"form\" : \"un\", \"lemma\" : \"un\", \"tag\" : \"DA0MS0\", \"ctag\" : \"DA\", \"pos\" : \"determiner\", \"type\" : \"article\", \"gen\" : \"masculine\", \"num\" : \"singular\"},\n" +
                "{ \"id\" : \"t1.3\", \"begin\" : \"8\", \"end\" : \"13\", \"form\" : \"testo\", \"lemma\" : \"testo\", \"tag\" : \"NCMS000\", \"ctag\" : \"NC\", \"pos\" : \"noun\", \"type\" : \"common\", \"gen\" : \"masculine\", \"num\" : \"singular\"},\n" +
                "{ \"id\" : \"t1.4\", \"begin\" : \"14\", \"end\" : \"15\", \"form\" : \".\", \"lemma\" : \".\", \"tag\" : \"Fp\", \"ctag\" : \"Fp\", \"pos\" : \"punctuation\", \"type\" : \"period\"}]}]}");


    }

    @Test(timeout=5000)
    public void getAutomaticCorrection() throws IOException, JSONException {

        String myTest = new String("sono un testo");
        SolutionModel myAutoSolution = solutionService.getAutomaticCorrection(myTest);
        JSONAssert.assertEquals(mysolution, myAutoSolution.getSolutionText(), JSONCompareMode.LENIENT);
    }

}