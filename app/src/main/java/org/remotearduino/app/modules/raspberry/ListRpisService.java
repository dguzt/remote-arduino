package org.remotearduino.app.modules.raspberry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.remotearduino.app.modules.common.pagination.Page;
import org.remotearduino.app.modules.common.pagination.PagePointer;
import org.remotearduino.app.modules.raspberry.domain.Rpi;
import org.remotearduino.app.modules.raspberry.domain.RpiAvailability;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListRpisService implements ListRpisUseCase {

    @Override
    public Mono<Page<Rpi>> execute(List<RpiAvailability> availabilities, PagePointer pagePointer) {
        return Mono.empty();
    }
}
