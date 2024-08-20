package org.remotearduino.app.modules.raspberry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.remotearduino.app.modules.raspberry.domain.Rpi;
import org.remotearduino.app.modules.raspberry.domain.RpiAvailability;
import org.remotearduino.app.modules.raspberry.ports.SaveRpiPort;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterRpiServiceTest {

    @InjectMocks
    private RegisterRpiService registerRpiService;

    @Mock
    private SaveRpiPort saveRpiPort;

    @Test
    void whenTryToRegisterRpi_andItWasNotRegisteredBefore_thenShouldRegisterRpi() {
        var rpiToRegister = new RpiToRegister("10.10.10.10", "raspbian", "1111-2222-3333-4444");

        var rpiId = "123456";
        var rpiRegistered = new Rpi(rpiId, rpiToRegister.ip(), rpiToRegister.os(), rpiToRegister.startId(), RpiAvailability.REGISTERED);

        when(saveRpiPort.existsByStartId(any())).thenReturn(Mono.just(false));
        when(saveRpiPort.save(any(), any())).thenReturn(Mono.just(rpiRegistered));

        registerRpiService.execute(rpiToRegister)
                .as(StepVerifier::create)
                .assertNext(r -> {
                    assertThat(r).isNotNull();
                    assertThat(r.id()).isEqualTo(rpiId);
                })
                .verifyComplete();
    }

    @Test
    void whenTryToRegisterRpi_andItWasAlreadyRegistered_thenFailWithRpiThrowAlreadyRegistered() {
        var rpiToRegister = new RpiToRegister("10.10.10.10", "raspbian", "1111-2222-3333-4444");

        when(saveRpiPort.existsByStartId(any())).thenReturn(Mono.just(true));

        registerRpiService.execute(rpiToRegister)
                .as(StepVerifier::create)
                .expectErrorMatches(e -> e instanceof RpiAlreadyRegisteredException)
                .verify();
    }
}
