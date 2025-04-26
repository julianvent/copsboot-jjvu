package mx.jjvu.copsboot.controllers;

import jakarta.validation.Valid;
import mx.jjvu.copsboot.model.report.Report;
import mx.jjvu.copsboot.model.report.ReportDto;
import mx.jjvu.copsboot.model.user.User;
import mx.jjvu.copsboot.services.CreateReportParameters;
import mx.jjvu.copsboot.services.CreateReportRequest;
import mx.jjvu.copsboot.services.ReportService;
import mx.jjvu.copsboot.services.UserService;
import mx.jjvu.copsboot.utility.id.AuthServerId;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/reports")
public class ReportRestController {
    private final ReportService service;
    private final UserService userService;

    public ReportRestController(ReportService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReportDto createReport(
            @AuthenticationPrincipal Jwt jwt,
            @Valid CreateReportRequest request
    ) {
        AuthServerId authServerId = new AuthServerId(UUID.fromString(jwt.getSubject()));

        User user = userService.findUserByAuthServerId(authServerId).orElseThrow(RuntimeException::new);

        CreateReportParameters parameters = request.toParameters(user.getId());
        Report report = service.createReport(parameters);
        return ReportDto.fromReport(report, userService);
    }
}
