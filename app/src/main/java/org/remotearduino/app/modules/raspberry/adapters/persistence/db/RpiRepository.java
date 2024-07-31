package org.remotearduino.app.modules.raspberry.adapters.persistence.db;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface RpiRepository extends ReactiveMongoRepository<RpiDoc, String> {
    Mono<Boolean> existsByStartId(String startId);
}
