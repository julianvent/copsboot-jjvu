package mx.jjvu.copsboot.configuration;

import com.c4_soft.springaddons.security.oidc.starter.synchronised.resourceserver.ResourceServerExpressionInterceptUrlRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    @Bean
    ResourceServerExpressionInterceptUrlRegistryPostProcessor authorizePostProcessor() {
        return registry -> registry
                .requestMatchers(HttpMethod.OPTIONS, "/api/**")
                .permitAll()
                .requestMatchers("/api/**")
                .authenticated()
                .anyRequest()
                .authenticated();
    }
}
