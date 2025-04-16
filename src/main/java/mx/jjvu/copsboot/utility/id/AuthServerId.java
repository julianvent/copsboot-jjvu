package mx.jjvu.copsboot.utility.id;

import org.springframework.util.Assert;

import java.util.UUID;

public record AuthServerId(UUID value) {
    public AuthServerId {
        Assert.notNull(value, "The AuthServerId value should not be null");
    }
}
