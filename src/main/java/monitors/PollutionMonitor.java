package monitors;

import HES.Systems.EnvironmentalSystem;
import model.MonitorResult;

import java.util.Locale;

public class PollutionMonitor extends EnvironmentalMonitor {

    public PollutionMonitor(EnvironmentalSystem system) {
        super(system);
    }

    @Override
    public MonitorResult update() {
        float pollution = system.getPollutionFeed().getPollution();

        boolean warning = pollution > 5.0f;

        if (system.isAirPurifierOn() != warning) {
            system.turnAirPurifierOn(warning);
        }

        return new MonitorResult(
                "Air Pollution (PM2.5)",
                warning,
                String.format(Locale.US, "Current: %.1f", pollution),
                "Maximum: 5.0",
                "Air purifier: "
                        + (system.isAirPurifierOn() ? "ON" : "OFF")
        );
    }
}