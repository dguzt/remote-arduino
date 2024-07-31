package org.remotearduino.app.modules.raspberry.adapters.web.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.remotearduino.app.modules.raspberry.RpiToRegister;

@Mapper(componentModel = "spring")
public interface RpiDtoMapper {

    @Mapping(target = "ip", source = "ip")
    @Mapping(target = "os", source = "os")
    @Mapping(target = "startId", source = "startId")
    RpiToRegister toRpi(RpiToRegisterBody rpiToRegisterBody);
}
