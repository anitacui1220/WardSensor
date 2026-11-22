package monitors;

import HES.Systems.EnvironmentalSystem;
import model.MonitorResult;
import model.Ward;

import java.util.Locale;

public class TemperatureMonitor extends EnvironmentalMonitor {
    private final Ward ward;

    public TemperatureMonitor(EnvironmentalSystem system, Ward ward) {
        super(system);
        this.ward = ward;
    }

    @Override
    public MonitorResult update() {
        int id = ward.getId();
        float target = ward.getTargetTemperature();
        float current = system.getTempFeed().getTemperature(id);

        // Heating control with +/-0.5 degree tolerance
        if (current < target - 0.5f) {
            if (!system.isHeatingOn(id)) {
                system.turnHeatingOn(id, true);
            }
        } else if (current > target + 0.5f) {
            if (system.isHeatingOn(id)) {
                system.turnHeatingOn(id, false);
            }
        }

        boolean warning = Math.abs(current - target) > 0.5f;

        return new MonitorResult(
                ward.getName(),
                warning,
                String.format(Locale.US, "Current: %.1f C", current),
                String.format(Locale.US, "Ideal: %.1f C", target),
                "Heating: " + (system.isHeatingOn(id) ? "ON" : "OFF")
        );
    }
}