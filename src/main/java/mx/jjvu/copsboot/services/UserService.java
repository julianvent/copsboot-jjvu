package mx.jjvu.copsboot.services;

import mx.jjvu.copsboot.model.user.User;
import mx.jjvu.copsboot.repositories.user.UserRepository;
import mx.jjvu.copsboot.utility.id.AuthServerId;
import mx.jjvu.copsboot.utility.id.UserId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public Optional<User> findUserByAuthServerId(AuthServerId authServerId) {
        return repository.findByAuthServerId(authServerId);
    }

    public User createUser(CreateUserParameters createUserParameters) {
        UserId userId = repository.nextId();
        User user = new User(
                userId,
                createUserParameters.email(),
                createUserParameters.authServerId(),
                createUserParameters.mobileToken()
        );
        return repository.save(user);
    }
}
