package mx.jjvu.copsboot.utility.id;

import java.util.UUID;

public class ReportId extends AbstractEntityId<UUID> {
    protected ReportId() {}

    public ReportId(UUID id) {
        super(id);
    }
}
