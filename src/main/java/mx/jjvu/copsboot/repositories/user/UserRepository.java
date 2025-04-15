package mx.jjvu.copsboot.repositories.user;

import mx.jjvu.copsboot.utility.id.UserId;
import org.springframework.data.repository.CrudRepository;

import mx.jjvu.copsboot.model.user.User;

public interface UserRepository extends CrudRepository<User, UserId>, UserRepositoryCustom {}
