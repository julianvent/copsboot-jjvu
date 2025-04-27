package mx.jjvu.copsboot;

import mx.jjvu.copsboot.infrastructure.SpringProfiles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
public class CopsbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopsbootApplication.class, args);
    }

}
