package org.remotearduino.app.modules.raspberry;

import org.remotearduino.app.modules.common.pagination.Page;
import org.remotearduino.app.modules.common.pagination.PagePointer;
import org.remotearduino.app.modules.raspberry.domain.Rpi;
import org.remotearduino.app.modules.raspberry.domain.RpiAvailability;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ListRpisUseCase {
    Mono<Page<Rpi>> execute(List<RpiAvailability> availabilities, PagePointer pagePointer);
}
