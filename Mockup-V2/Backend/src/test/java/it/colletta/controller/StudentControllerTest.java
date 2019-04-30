package it.colletta.controller;


import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;


import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.helper.FavoriteTeacherHelper;
import java.util.Arrays;
import java.util.Date;

import it.colletta.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

  private MockMvc mvc;
  private String userToken;
  private ObjectMapper mapper;
  private FavoriteTeacherHelper favoriteTeacherHelper;

  @Mock
  private UserService userService;

  @InjectMocks
  private StudentController studentController;

  @Before
  public void setup() {
    userToken = ("Bearer") + JWT.create().withJWTId("test@test.it").withSubject("test")
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));

    mapper = new ObjectMapper();
    mvc = MockMvcBuilders.standaloneSetup(studentController)
            .build();

    favoriteTeacherHelper = FavoriteTeacherHelper.builder()
            .teacherId(Arrays.asList("1", "2"))
            .build();
  }

  //@Test
  //@WithMockUser(roles={"STUDENT"})
 // public void ModifyFavoriteTeachersTest() {
    /*try {
      String jsonFavoriteTeacherHelper = mapper.writeValueAsString(favoriteTeacherHelper);
      mvc.perform(MockMvcRequestBuilders.put("/students/favorite-teacher")
              .header("Authorization", userToken)
              .content(jsonFavoriteTeacherHelper)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    } */
  //}

  /*
  @Test
  @WithMockUser(roles={"STUDENT"})
  public void GetFavoriteTeachersTest() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/students/favorite-teacher")
              .header("Authorization", userToken)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

   */

}
