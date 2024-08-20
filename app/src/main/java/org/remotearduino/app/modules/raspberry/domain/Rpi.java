package org.remotearduino.app.modules.raspberry.domain;

public record Rpi(
        String id,
        String ip,
        String os,
        String startId,
        RpiAvailability availability
) {
}
