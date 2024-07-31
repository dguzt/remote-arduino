package org.remotearduino.app.modules.common.hexagonal;

public record ErrorResponse(
        String code,
        String message
) {
}
