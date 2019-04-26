package it.colletta.controller;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.auth0.jwt.JWT;
import it.colletta.service.PhraseService;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class PhraseControllerTest {

  private String userToken;
  private MockMvc mvc;

  @Mock
  private PhraseService phraseService;

  @InjectMocks
  private PhraseController phraseController;

  @Before
  public void setUp() {
    userToken = ("Bearer") + JWT.create().withJWTId("test@test.it").withSubject("test")
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(HMAC512(SECRET.getBytes()));

    mvc = MockMvcBuilders.standaloneSetup(phraseController).build();

  }

  @Test
  public void downloadAllPhrasesTest() {
    try {
      mvc.perform(
          MockMvcRequestBuilders.get("/phrases/downloadAll").header("Authorization", userToken))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
