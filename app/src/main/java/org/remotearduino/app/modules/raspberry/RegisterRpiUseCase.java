package org.remotearduino.app.modules.raspberry;

import org.remotearduino.app.modules.raspberry.domain.Rpi;
import reactor.core.publisher.Mono;

public interface RegisterRpiUseCase {
    Mono<Rpi> execute(RpiToRegister rpiToRegister);
}
