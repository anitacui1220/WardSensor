package monitors;

import HES.Systems.EnvironmentalSystem;
import model.MonitorResult;

public abstract class EnvironmentalMonitor {
    protected final EnvironmentalSystem system;

    public EnvironmentalMonitor(EnvironmentalSystem system) {
        this.system = system;
    }

    public abstract MonitorResult update();
}
