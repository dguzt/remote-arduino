package org.remotearduino.app.modules.raspberry.ports;

import org.remotearduino.app.modules.raspberry.RpiToRegister;
import org.remotearduino.app.modules.raspberry.domain.Rpi;
import reactor.core.publisher.Mono;

public interface SaveRpiPort {
    Mono<Boolean> existsByStartId(String startId);
    Mono<Rpi> save(RpiToRegister rpiToRegister);
}
