package mx.jjvu.copsboot;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import io.restassured.RestAssured;
import mx.jjvu.copsboot.infrastructure.SpringProfiles;
import mx.jjvu.copsboot.repositories.user.UserRepository;
import mx.jjvu.copsboot.services.KeycloakAdminClientFacade;
import org.junit.jupiter.api.*;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(SpringProfiles.INTEGRATION_TEST)
public class ReportRestControllerIntegrationTest {
    private static final String REALM_NAME = "copsboot";
    private static final String ROLE_NAME = "OFFICER";
    private static final String INTEGRATION_TEST_CLIENT_ID = "integration-test-client";
    private static final String TEST_USER_NAME = "hola@ejemplo.com";
    private static final String TEST_USER_PASSWORD = "password123";
    static KeycloakContainer keycloak = new KeycloakContainer("quay.io/keycloak/keycloak:latest");
    private static String clientSecret;
    @LocalServerPort
    private int serverport;

    @BeforeAll
    static void beforeAll() {
        keycloak.start();
        Keycloak client = keycloak.getKeycloakAdminClient();

        KeycloakAdminClientFacade clientFacade = new KeycloakAdminClientFacade(client);

        clientFacade.createRealm(REALM_NAME);
        clientFacade.createRealmRole(REALM_NAME, ROLE_NAME);
        clientFacade.createUser(REALM_NAME, TEST_USER_NAME, TEST_USER_PASSWORD, ROLE_NAME);
        clientSecret = clientFacade.createClient(REALM_NAME, INTEGRATION_TEST_CLIENT_ID);
    }

    @AfterAll
    static void afterAll() {
        keycloak.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add(
                "com.c4-soft.springaddons.oidc.ops[0].iss",
                () -> keycloak.getAuthServerUrl() + "/realms/" + REALM_NAME
        );
        registry.add("com.c4-soft.springaddons.oidc.ops[0].authorities[0].path", () -> "$.realm_access.roles");
        registry.add("com.c4-soft.springaddons.oidc.ops[0].authorities[0].prefix", () -> "ROLE_");
    }

    @AfterEach
    void afterEach(@Autowired UserRepository userRepository) {
        userRepository.deleteAll();
    }

    @BeforeEach
    public void setup() {
        RestAssured.port = serverport;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void init() {

    }
}
