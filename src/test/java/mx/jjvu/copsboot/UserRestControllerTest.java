package mx.jjvu.copsboot;

import com.c4_soft.springaddons.security.oauth2.test.webmvc.AutoConfigureAddonsWebmvcClientSecurity;
import com.c4_soft.springaddons.security.oauth2.test.webmvc.AutoConfigureAddonsWebmvcResourceServerSecurity;
import mx.jjvu.copsboot.configuration.WebSecurityConfiguration;
import mx.jjvu.copsboot.controllers.UserRestController;
import mx.jjvu.copsboot.model.user.User;
import mx.jjvu.copsboot.services.CreateUserParameters;
import mx.jjvu.copsboot.services.UserService;
import mx.jjvu.copsboot.utility.id.AuthServerId;
import mx.jjvu.copsboot.utility.id.UserId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
@AutoConfigureAddonsWebmvcResourceServerSecurity
@Import(WebSecurityConfiguration.class)
public class UserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void givenUnauthenticatedUser_userInfoEndpointReturnsUnauthorized() throws Exception {
        mockMvc.perform(get("/api/users/me")).andExpect(status().isUnauthorized());
    }

    @Test
    void givenAuthenticatedUser_userInfoEndpointReturnsOk() throws Exception {
        String subject = UUID.randomUUID().toString();
        mockMvc
                .perform(get("/api/users/me").with(jwt().jwt(builder -> builder.subject(subject))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("subject").value(subject))
                .andExpect(jsonPath("claims").isMap());
    }

    @Test
    void givenAuthenticatedOfficer_userIsCreated() throws Exception {
        UserId userId = new UserId(UUID.randomUUID());
        when(userService.createUser(any(CreateUserParameters.class))).thenReturn(new User(
                userId,
                "hola@ejemplo.com",
                new AuthServerId(UUID.fromString("eaa8b8a5-a264-48be-98de-d8b4ae2750ac")),
                "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0"
        ));

        mockMvc.perform(post("/api/users")
                .with(jwt()
                        .jwt(builder -> builder.subject(UUID.randomUUID().toString()))
                        .authorities(new SimpleGrantedAuthority("ROLE_OFFICER")))
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                           "mobileToken": "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0"
                         }
                        """)).andExpect(status().isCreated()).andExpect(jsonPath("userId").value(userId.asString()));
    }

    @Test
    void givenAuthenticatedUserThatIsNotAnOfficer_forbiddenIsReturned() throws Exception {
        mockMvc.perform(post("/api/users")
                .with(jwt().jwt(builder -> builder.subject(UUID.randomUUID().toString())))
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "mobileToken" :  "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0"
                        }
                        """)).andExpect(status().isForbidden());
    }
}
