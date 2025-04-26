package mx.jjvu.copsboot.services;

import mx.jjvu.copsboot.model.report.Report;
import mx.jjvu.copsboot.repositories.report.ReportRepository;
import mx.jjvu.copsboot.utility.id.ReportId;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public Report createReport(CreateReportParameters createReportParameters) {
        ReportId id = repository.nextId();
        Report report = new Report(
                id,
                createReportParameters.reporterId(),
                createReportParameters.dateTime(),
                createReportParameters.description()
        );
        return repository.save(report);
    }
}
