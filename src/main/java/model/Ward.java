package model;

public class Ward {
    private final int id;
    private final String name;
    private final float targetTemperature;

    public Ward(int id, String name, float targetTemperature) {
        this.id = id;
        this.name = name;
        this.targetTemperature = targetTemperature;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getTargetTemperature() {
        return targetTemperature;
    }
}
