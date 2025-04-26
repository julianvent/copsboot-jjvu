package mx.jjvu.copsboot;

import mx.jjvu.copsboot.annotations.CopsbootControllerTest;
import mx.jjvu.copsboot.controllers.ReportRestController;
import mx.jjvu.copsboot.model.report.Report;
import mx.jjvu.copsboot.model.user.User;
import mx.jjvu.copsboot.services.CreateReportParameters;
import mx.jjvu.copsboot.services.ReportService;
import mx.jjvu.copsboot.services.UserService;
import mx.jjvu.copsboot.utility.id.AuthServerId;
import mx.jjvu.copsboot.utility.id.ReportId;
import mx.jjvu.copsboot.utility.id.UserId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CopsbootControllerTest(ReportRestController.class)
public class ReportRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ReportService service;
    @MockitoBean
    private UserService userService;

    @Test
    void officeIsAbleToPostAReport() throws Exception {
        UserId userId = new UserId(UUID.randomUUID());
        AuthServerId authServerId = new AuthServerId(UUID.fromString("eaa8b8a5-a264-48be-98de-d8b4ae2750ac"));
        User user = new User(
                userId,
                "hola@ejemplo.com",
                authServerId,
                "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0"
        );
        when(userService.findUserByAuthServerId(authServerId)).thenReturn(Optional.of(user));
        when(userService.getUserById(userId)).thenReturn(user);
        when(service.createReport(any(CreateReportParameters.class))).thenReturn(new Report(
                new ReportId(UUID.randomUUID()),
                userId,
                Instant.parse("2023-04-11T22:59:03.189+02:00"),
                "This is a test report description."
        ));

        mockMvc
                .perform(multipart("/api/reports")
                        .file(new MockMultipartFile(
                                "image",
                                "picture.png",
                                MediaType.IMAGE_PNG_VALUE,
                                new byte[]{1, 2, 3}
                        ))
                        .param("dateTime", "2023-04-11T22:59:03.189+02:00")
                        .param("description", "This is a test report description.")
                        .param("trafficIncident", "false")
                        .param("involvedCars", "0")
                        .with(jwt()
                                .jwt(builder -> builder
                                        .subject(authServerId.value().toString())
                                        .claim("email", "hola@ejemplo.com"))
                                .authorities(new SimpleGrantedAuthority("ROLE_OFFICER"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("reporter").value("hola@ejemplo.com"))
                .andExpect(jsonPath("dateTime").value("2023-04-11T20:59:03.189Z"))
                .andExpect(jsonPath("description").value("This is a test report description."));
    }
}
