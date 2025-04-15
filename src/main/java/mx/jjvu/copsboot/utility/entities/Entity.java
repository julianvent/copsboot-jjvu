package mx.jjvu.copsboot.utility.entities;

import mx.jjvu.copsboot.utility.id.EntityId;

public interface Entity<T extends EntityId> {
    T getId();
}
