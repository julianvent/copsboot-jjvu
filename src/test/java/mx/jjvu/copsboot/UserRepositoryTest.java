package mx.jjvu.copsboot;

import java.util.HashSet;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import mx.jjvu.copsboot.model.user.User;
import mx.jjvu.copsboot.model.user.UserRole;
import mx.jjvu.copsboot.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testStoreUser() {
        HashSet<UserRole> roles = new HashSet<>();
        roles.add(UserRole.OFFICER);

        User user = repository.save(new User(
                UUID.randomUUID(),
                "Julian Ventura",
                "julianvent@gmail.com",
                "password123",
                roles
        ));

        assertThat(user).isNotNull();

        assertThat(repository.count()).isEqualTo(1L);
    }
}
