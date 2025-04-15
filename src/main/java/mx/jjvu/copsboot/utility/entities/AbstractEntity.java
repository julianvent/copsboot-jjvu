package mx.jjvu.copsboot.utility.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import mx.jjvu.copsboot.utility.annotations.ArtifactFromFramework;
import mx.jjvu.copsboot.utility.id.EntityId;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

@MappedSuperclass
public class AbstractEntity<T extends EntityId> implements Entity<T> {
    @EmbeddedId
    private T id;

    @ArtifactFromFramework
    protected AbstractEntity() {}

    public AbstractEntity(T id) {
        this.id = checkNotNull(id);
    }

    @Override
    public T getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = true;

        if (this == obj) {
            result = true;
        } else if (obj instanceof AbstractEntity other) {
            result = Objects.equals(id, other.id);
        }

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("id", id).toString();
    }
}
