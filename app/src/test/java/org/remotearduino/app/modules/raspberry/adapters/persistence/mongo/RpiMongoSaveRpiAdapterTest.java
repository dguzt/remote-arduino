package org.remotearduino.app.modules.raspberry.adapters.persistence.mongo;

import org.junit.jupiter.api.Test;
import org.remotearduino.app.TestcontainersConfiguration;
import org.remotearduino.app.modules.raspberry.TestRpiFactory;
import org.remotearduino.app.modules.raspberry.domain.RpiAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Import(TestcontainersConfiguration.class)
@ComponentScan(basePackageClasses = RpiMapper.class)
class RpiMongoSaveRpiAdapterTest {

    @Autowired
    private RpiMongoSaveRpiAdapter rpiMongoSaveRpiAdapter;

    @Test
    void testExistsRpiInMono_andNotExists_thenIsNotFound_andReturnFalse() {
        var rpiToRegister = TestRpiFactory.createDefaultRpiToRegister();

        // Check that not exists
        var noExists = rpiMongoSaveRpiAdapter.existsByStartId(rpiToRegister.startId());
        noExists.as(StepVerifier::create)
                .assertNext(r -> assertThat(r).isFalse())
                .verifyComplete();

        // Register the Rpi
        var registered = rpiMongoSaveRpiAdapter.save(rpiToRegister, RpiAvailability.REGISTERED);
        registered.as(StepVerifier::create)
                .assertNext(r -> {
                    assertThat(r).isNotNull();
                    assertThat(r.id()).isNotEmpty();
                })
                .verifyComplete();

        // Check that exists
        var exists = rpiMongoSaveRpiAdapter
                .save(rpiToRegister, RpiAvailability.REGISTERED)
                .flatMap(r -> rpiMongoSaveRpiAdapter.existsByStartId(r.startId()));

        exists.as(StepVerifier::create)
                .assertNext(r -> assertThat(r).isTrue())
                .verifyComplete();
    }
}
