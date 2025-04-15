package mx.jjvu.copsboot.utility.id;

import java.io.Serializable;

public interface EntityId<T> extends Serializable {
    T getId();

    String asString();
}
