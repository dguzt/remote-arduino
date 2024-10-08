package org.remotearduino.app.modules.raspberry.adapters.persistence.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface RpiRepository extends ReactiveMongoRepository<RpiDocument, String> {
    Mono<Boolean> existsByStartId(String startId);
}
