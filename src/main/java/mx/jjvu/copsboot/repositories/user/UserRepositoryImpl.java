package mx.jjvu.copsboot.repositories.user;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;
import mx.jjvu.copsboot.utility.id.UserId;

import java.util.UUID;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public UserRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public UserId nextId() {
        return new UserId(generator.getNextUniqueId());
    }
}
