package mx.jjvu.copsboot;

import java.util.UUID;

import io.github.wimdeblauwe.jpearl.InMemoryUniqueIdGenerator;
import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;
import mx.jjvu.copsboot.utility.id.AuthServerId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import mx.jjvu.copsboot.model.user.User;
import mx.jjvu.copsboot.repositories.user.UserRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testStoreUser() {
        User user = repository.save(new User(
                repository.nextId(),
                "hola@ejemplo.com",
                new AuthServerId(UUID.fromString("eaa8b8a5-a264-48be-98de-d8b4ae2750ac")),
                "c41536a5a8b9d3f14a7e5472a5322b5e1f76a6e7a9255c2c2e7e0d3a2c5b9d0"
        ));

        assertThat(user).isNotNull();

        assertThat(repository.count()).isEqualTo(1L);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public UniqueIdGenerator<UUID> generator() {
            return new InMemoryUniqueIdGenerator();
        }
    }
}
