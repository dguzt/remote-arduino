package org.remotearduino.app.modules.raspberry.adapters.web.rest;

import lombok.RequiredArgsConstructor;
import org.remotearduino.app.modules.raspberry.RegisterRpiUseCase;
import org.remotearduino.app.modules.raspberry.domain.Rpi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegisterRpiRestController {
    private final RegisterRpiUseCase registerRpiUseCase;
    private final RpiDtoMapper rpiDtoMapper;

    @PostMapping("/raspberries")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Rpi> registerRpi(@RequestBody RpiToRegisterBody rpiToRegisterBody) {
        var rpiToRegister = rpiDtoMapper.toRpi(rpiToRegisterBody);
        return registerRpiUseCase.execute(rpiToRegister);
    }
}
