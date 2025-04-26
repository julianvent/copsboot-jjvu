package mx.jjvu.copsboot;

import java.util.UUID;

import io.github.wimdeblauwe.jpearl.InMemoryUniqueIdGenerator;
import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mx.jjvu.copsboot.infrastructure.SpringProfiles;
import mx.jjvu.copsboot.utility.id.AuthServerId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import mx.jjvu.copsboot.model.user.User;
import mx.jjvu.copsboot.repositories.user.UserRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(SpringProfiles.REPOSITORY_TEST)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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

        entityManager.flush();

        assertThat(jdbcTemplate.queryForObject("SELECT count(*) FROM copsboot_user", long.class)).isEqualTo(1L);

        assertThat(jdbcTemplate.queryForObject("SELECT email FROM copsboot_user", String.class)).isEqualTo(
                "hola@ejemplo.com");
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public UniqueIdGenerator<UUID> generator() {
            return new InMemoryUniqueIdGenerator();
        }
    }
}
