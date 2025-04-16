package mx.jjvu.copsboot.model.user;

import mx.jjvu.copsboot.utility.id.AuthServerId;
import mx.jjvu.copsboot.utility.id.UserId;

public record UserDto(UserId userId, String email, AuthServerId authServerId, String mobileToken) {
    public static UserDto fromUser(User user) {
        return new UserDto(user.getId(), user.getEmail(),user.getAuthServerId(), user.getMobileToken());
    }
}
