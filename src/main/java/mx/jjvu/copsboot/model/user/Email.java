package mx.jjvu.copsboot.model.user;

import org.springframework.util.Assert;

public record Email(String email) {
    public Email {
        Assert.notNull(email, "Email should not be null");
    }
}
