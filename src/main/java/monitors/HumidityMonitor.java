package monitors;

import HES.Systems.EnvironmentalSystem;
import model.MonitorResult;

public class HumidityMonitor extends EnvironmentalMonitor {

    public HumidityMonitor(EnvironmentalSystem system) {
        super(system);
    }

    @Override
    public MonitorResult update() {
        int inside = system.getHumidityFeed().getInsideHumidity();
        int outside = system.getHumidityFeed().getOutsideHumidity();
        int difference = inside - outside;

        boolean warning = difference > 10;

        if (system.isDehumidifierOn() != warning) {
            system.turnDehumidifierOn(warning);
        }

        return new MonitorResult(
                "Humidity",
                warning,
                "Inside: " + inside + "%",
                "Outside: " + outside + "%",
                "Difference: " + (difference >= 0 ? "+" : "")
                        + difference + " percentage points",
                "Dehumidifier: "
                        + (system.isDehumidifierOn() ? "ON" : "OFF")
        );
    }
}