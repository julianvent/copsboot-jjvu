package mx.jjvu.copsboot.repositories.report;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;
import mx.jjvu.copsboot.utility.id.ReportId;

import java.util.UUID;

public class ReportRepositoryImpl implements ReportRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public ReportRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public ReportId nextId() {
        return new ReportId(generator.getNextUniqueId());
    }
}
