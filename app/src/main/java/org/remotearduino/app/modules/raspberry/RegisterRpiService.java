package org.remotearduino.app.modules.raspberry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.remotearduino.app.modules.raspberry.domain.Rpi;
import org.remotearduino.app.modules.raspberry.ports.SaveRpiPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterRpiService implements RegisterRpiUseCase {
    private final SaveRpiPort saveRpiPort;

    @Override
    public Mono<Rpi> execute(RpiToRegister rpiToRegister) {
        return saveRpiPort.existsByStartId(rpiToRegister.startId())
                .flatMap(alreadyRegistered -> {
                    if (Boolean.TRUE.equals(alreadyRegistered)) {
                        var alreadyRegisteredException = new RpiAlreadyRegisteredException(rpiToRegister.startId());
                        return Mono.error(alreadyRegisteredException);
                    }

                    return saveRpiPort.save(rpiToRegister);
                });
    }
}
