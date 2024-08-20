package org.remotearduino.app.modules.raspberry.adapters.persistence.db;

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
    RpiDocument toDocument(RpiToRegister rpiToRegister);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ip", source = "ip")
    @Mapping(target = "os", source = "os")
    @Mapping(target = "startId", source = "startId")
    Rpi toRpi(RpiDocument rpiDoc);
}
