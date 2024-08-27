package org.remotearduino.app.modules.raspberry.adapters.web.rest;

import org.junit.jupiter.api.Test;
import org.remotearduino.app.config.security.HttpSecurityConfig;
import org.remotearduino.app.modules.raspberry.RegisterRpiUseCase;
import org.remotearduino.app.modules.raspberry.TestRpiFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(RegisterRpiRestController.class)
@Import(HttpSecurityConfig.class)
@ComponentScan(basePackageClasses = RpiDtoMapper.class)
class RegisterRpiRestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private RegisterRpiUseCase registerRpiUseCase;

    @Test
    void whenRegisterRpi_andItWasNotRegisteredBefore_thenRegisterIt_andReturnOk200() {
        var rpiToRegister = TestRpiFactory.createDefaultRpiToRegister();

        var rpiRegistered = TestRpiFactory.createDefaultRpi();
        when(registerRpiUseCase.execute(any())).thenReturn(Mono.just(rpiRegistered));

        webTestClient.post()
                .uri("/api/v1/raspberries")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(rpiToRegister)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.startId").isEqualTo(rpiToRegister.startId());
    }
}