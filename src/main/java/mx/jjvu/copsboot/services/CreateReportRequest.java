package mx.jjvu.copsboot.services;

import jakarta.validation.constraints.NotNull;
import mx.jjvu.copsboot.utility.annotations.ValidReportDescription;
import mx.jjvu.copsboot.utility.id.UserId;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

public record CreateReportRequest(Instant dateTime, boolean trafficIncident, int involvedCars,
                                  String description, @NotNull MultipartFile image) {
    public CreateReportParameters toParameters(UserId userId) {
        return new CreateReportParameters(userId, dateTime, description);
    }
}
