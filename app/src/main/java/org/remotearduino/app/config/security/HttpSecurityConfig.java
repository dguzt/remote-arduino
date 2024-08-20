package org.remotearduino.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

@Configuration
@EnableWebFluxSecurity
public class SecurityHttpConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityWebFilterChain apiHttpSecurity(ServerHttpSecurity http) {
        var apiEndpoints = new PathPatternParserServerWebExchangeMatcher("/api/**");

        return http
                .securityMatcher(apiEndpoints)
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange()
                        .permitAll())
                .build();
    }
}
