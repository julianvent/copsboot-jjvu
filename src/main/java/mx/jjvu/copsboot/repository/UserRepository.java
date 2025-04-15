package mx.jjvu.copsboot.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import mx.jjvu.copsboot.model.user.User;

public interface UserRepository extends CrudRepository<User, UUID> {}
