package org.remotearduino.app.modules.raspberry.adapters.persistence.mongo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.remotearduino.app.modules.raspberry.RpiToRegister;
import org.remotearduino.app.modules.raspberry.domain.Rpi;

@Mapper(componentModel = "spring")
public interface RpiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ip", source = "ip")
    @Mapping(target = "os", source = "os")
    @Mapping(target = "startId", source = "startId")
    @Mapping(target = "availability", ignore = true)
    RpiDocument toDocument(RpiToRegister rpiToRegister);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ip", source = "ip")
    @Mapping(target = "os", source = "os")
    @Mapping(target = "startId", source = "startId")
    @Mapping(target = "availability", source = "availability")
    Rpi toRpi(RpiDocument rpiDoc);
}
