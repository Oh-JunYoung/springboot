package server.server.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebFluxSecurity
@Configuration
public class WebfluxSecurityConfig {
    @Bean
    public SecurityWebFilterChain springWebfluxSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors()

                .and()
                .exceptionHandling()

                .and()
                .authorizeExchange()
                .pathMatchers("/**").permitAll();

        return serverHttpSecurity.build();
    }
}
