package it.colletta.controller;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.UserModel;
import it.colletta.service.SingupService;
import it.colletta.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SignUpControllerTest {

  private MockMvc mvc;
  private String userToken;
  private ObjectMapper mapper;

  @Mock
  SingupService singupService;

  @InjectMocks
  SingnupController singnupController;

  @Before
  public void setup() {
    userToken = ("Bearer") + JWT.create()
            .withJWTId("test@test.it")
            .withSubject("test")
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));
    mapper = new ObjectMapper();
    mvc = MockMvcBuilders.standaloneSetup(singnupController)
            .alwaysExpect(status().isOk())
            .build();
  }

  @Test
  public void activateUserTest(){
    Mockito.doNothing().when(singupService).setEnabledToTrue(anyString());
    try {
      mvc.perform(MockMvcRequestBuilders.get("/sign-up/activate/{id}", "test")
              .header("Authorization", userToken));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
  @Test

  public void signUpTest() {
    try {
      UserModel fakeTestUser =
              UserModel.builder()
                      .email("prova@prova.it")
                      .firstName("gianni")
                      .lastName("pinotto")
                      .password("Provaprova")
                      .language("it")
                      .role("ROLE_STUDENT")
                      .enabled(false)
                      .dateOfBirth(new Date(02-04-2013))
                      .build();

      String jsonUser = mapper.writeValueAsString(fakeTestUser);
      Mockito.when(singupService.addUser(fakeTestUser, any())).thenReturn(fakeTestUser);
      mvc.perform(MockMvcRequestBuilders.post("/sign-up")
              .content(jsonUser));
              .param("id", "1")
              .param("username","prova@prova.it")
              .param("firstName","gianni")
              .param("lastName","pinotto")
              .param("password", "Provaprova")
              .param("language","it")
              .param("role","ROLE_STUDENT")
              .param("dateOfBirth","2018-01-01"))
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
        */
}