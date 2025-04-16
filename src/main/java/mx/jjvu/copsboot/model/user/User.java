package mx.jjvu.copsboot.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mx.jjvu.copsboot.utility.entities.AbstractEntity;
import mx.jjvu.copsboot.utility.id.AuthServerId;
import mx.jjvu.copsboot.utility.id.UserId;

@Entity
@Table(name = "copsboot_user")
public class User extends AbstractEntity<UserId> {
    private String email;
    private AuthServerId authServerId;
    private String mobileToken;

    protected User() {}

    public User(UserId id, String email, AuthServerId authServerId, String mobileToken) {
        super(id);
        this.email = email;
        this.authServerId = authServerId;
        this.mobileToken = mobileToken;
    }

    public String getMobileToken() {
        return mobileToken;
    }

    public String getEmail() {
        return email;
    }

    public AuthServerId getAuthServerId() {
        return authServerId;
    }
}
