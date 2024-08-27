package org.remotearduino.app.modules.raspberry.adapters.persistence.mongo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.remotearduino.app.modules.raspberry.RpiToRegister;
import org.remotearduino.app.modules.raspberry.domain.Rpi;
import org.remotearduino.app.modules.raspberry.domain.RpiAvailability;
import org.remotearduino.app.modules.raspberry.ports.SaveRpiPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class RpiMongoSaveRpiAdapter implements SaveRpiPort {

    private final RpiRepository rpiRepository;
    private final RpiMapper rpiMapper;

    @Override
    public Mono<Boolean> existsByStartId(String startId) {
        return rpiRepository.existsByStartId(startId);
    }

    @Override
    public Mono<Rpi> save(RpiToRegister rpiToRegister, RpiAvailability availabilityStatus) {
        var doc = rpiMapper.toDocument(rpiToRegister);
        doc.setAvailability(availabilityStatus);

        return rpiRepository.save(doc)
                .map(rpiMapper::toRpi);
    }
}
