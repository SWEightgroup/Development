package it.colletta.controller;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.UserModel;
import it.colletta.service.SingupService;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class SignUpControllerTest {

  @Mock
  SingupService singupService;
  @InjectMocks
  SingnupController singnupController;
  UserModel testUserModel;
  private MockMvc mvc;
  private String userToken;
  private ObjectMapper mapper;

  @Before
  public void setup() {
    userToken = ("Bearer") + JWT.create()
        .withJWTId("test@test.it")
        .withSubject("test")
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(HMAC512(SECRET.getBytes()));

    testUserModel = UserModel.builder()
        .email("prova@prova.it")
        .firstName("gianni")
        .lastName("pinotto")
        .password("Provaprova")
        .language("it")
        .role("ROLE_STUDENT")
        .enabled(false)
        .dateOfBirth(new Date(02 - 04 - 2013))
        .build();

    mapper = new ObjectMapper();
    mvc = MockMvcBuilders.standaloneSetup(singnupController)
        //.alwaysExpect(status().isOk())
        .build();
  }

  @Test
  public void activateUserTest() {
    Mockito.doNothing().when(singupService).setEnabledToTrue(anyString());
    try {
      mvc.perform(MockMvcRequestBuilders.get("/sign-up/activate/{id}", "test"))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
  @Test
  public void signUpTest() {
    try {

      String jsonUser = mapper.writeValueAsString(testUserModel);
      Mockito.when(singupService.addUser(testUserModel, ControllerLinkBuilder.linkTo(SingnupController.class))).thenReturn(testUserModel);
      mvc.perform(MockMvcRequestBuilders.post("/sign-up")
              .content(jsonUser)
              .contentType(MediaType.APPLICATION_JSON_VALUE));
    } catch (Exception e) {
      e.printStackTrace();
    }
  } */
}
