package org.remotearduino.app.modules.raspberry.adapters.persistence.db;


import lombok.Getter;
import lombok.Setter;
import org.remotearduino.app.modules.raspberry.domain.RpiAvailability;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("rpi")
public class RpiDocument {
    @Id
    private String id;
    private String ip;
    private String os;
    private String startId;
    private RpiAvailability availability;
}
