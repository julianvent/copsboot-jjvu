package mx.jjvu.copsboot.repositories.report;

import mx.jjvu.copsboot.model.report.Report;
import mx.jjvu.copsboot.utility.id.ReportId;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, ReportId>, ReportRepositoryCustom {

}
