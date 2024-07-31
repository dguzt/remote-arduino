package org.remotearduino.app.modules.common.hexagonal;

public interface BusinessException {
    String getCode();
    String getMessage();
}
