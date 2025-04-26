package mx.jjvu.copsboot.model.report;

import mx.jjvu.copsboot.services.UserService;
import mx.jjvu.copsboot.utility.id.ReportId;

import java.time.Instant;

public record ReportDto(ReportId id, String reporter, Instant dateTime, String description) {
    public static ReportDto fromReport(Report report, UserService userService) {
        return new ReportDto(
                report.getId(),
                userService.getUserById(report.getReporterId()).getEmail(),
                report.getDateTime(),
                report.getDescription()
        );
    }
}
