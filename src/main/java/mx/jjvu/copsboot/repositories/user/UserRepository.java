package mx.jjvu.copsboot.repositories.user;

import mx.jjvu.copsboot.utility.id.AuthServerId;
import mx.jjvu.copsboot.utility.id.UserId;
import org.springframework.data.repository.CrudRepository;

import mx.jjvu.copsboot.model.user.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, UserId>, UserRepositoryCustom {
    Optional<User> findByAuthServerId(AuthServerId authServerId);
}
