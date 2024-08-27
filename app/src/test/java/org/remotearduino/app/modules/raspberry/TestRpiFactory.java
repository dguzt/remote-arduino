package org.remotearduino.app.modules.raspberry;


import org.remotearduino.app.modules.raspberry.domain.Rpi;
import org.remotearduino.app.modules.raspberry.domain.RpiAvailability;

public class TestRpiFactory {

    public static RpiToRegister createDefaultRpiToRegister() {
        return new RpiToRegister("10.10.10.10", "raspbian", "1111-2222-3333-4444");
    }

    public static Rpi createDefaultRpi() {
        return new Rpi("1234567890", "10.10.10.10", "raspbian", "1111-2222-3333-4444", RpiAvailability.REGISTERED);
    }
}
