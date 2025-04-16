package mx.jjvu.copsboot.model.user;

import org.springframework.util.Assert;

public record MobileToken(String mobileToken) {
    public MobileToken {
        Assert.notNull(mobileToken, "Mobile token should not be null");
    }
}
