package org.remotearduino.app.modules.raspberry.adapters.web.rest;

import jakarta.validation.constraints.NotBlank;

public record RpiToRegisterBody(
        @NotBlank
        String ip,
        @NotBlank
        String os,
        @NotBlank
        String startId
) {
}
