package mx.jjvu.copsboot.services;

import jakarta.validation.constraints.NotEmpty;
import mx.jjvu.copsboot.utility.id.AuthServerId;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public record CreateUserRequest(@NotEmpty String mobileToken) {

    public CreateUserParameters toParameters(Jwt jwt) {
        AuthServerId authServerId = new AuthServerId(UUID.fromString(jwt.getSubject()));
        String email = jwt.getClaimAsString("email");
        return new CreateUserParameters(authServerId, email, mobileToken);
    }
}
