package org.remotearduino.app.modules.raspberry;

import lombok.Getter;
import org.remotearduino.app.modules.common.hexagonal.InputBusinessException;

@Getter
public class RpiAlreadyRegisteredException extends RuntimeException implements InputBusinessException {
    private final String code;
    private final String message;

    public RpiAlreadyRegisteredException(String startId) {
        this.code = "RPI_001_ALREADY_REGISTERED";
        this.message = "Rpi with startId " + startId + " already registered";
    }
}
