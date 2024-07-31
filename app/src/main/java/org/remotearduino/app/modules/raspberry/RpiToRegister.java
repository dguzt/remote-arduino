package org.remotearduino.app.modules.raspberry;

public record RpiToRegister(
        String ip,
        String os,
        String startId
) {}
