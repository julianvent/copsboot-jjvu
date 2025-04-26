package mx.jjvu.copsboot.model.report;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mx.jjvu.copsboot.utility.annotations.ArtifactFromFramework;
import mx.jjvu.copsboot.utility.entities.AbstractEntity;
import mx.jjvu.copsboot.utility.id.ReportId;
import mx.jjvu.copsboot.utility.id.UserId;

import java.time.Instant;

@Entity
public class Report extends AbstractEntity<ReportId> {
    private UserId reporterId;
    private Instant dateTime;
    private String description;

    @ArtifactFromFramework
    protected Report() {}

    public Report(ReportId id, UserId reporterId, Instant dateTime, String description) {
        super(id);
        this.reporterId = reporterId;
        this.dateTime = dateTime;
        this.description = description;
    }

    public UserId getReporterId() {
        return reporterId;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }
}
