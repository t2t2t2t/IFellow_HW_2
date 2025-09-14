package Cars;

public class Tesla extends Car {
    @Override
    public String toString() {
        return super.toString() +
                " hasAutopilot=" + hasAutopilot +
                ", batteryCapacity=" + batteryCapacity;
    }

    boolean hasAutopilot;
    int batteryCapacity;

    public Tesla(String model, int year, String color, int horsepower, boolean isAutomatic, double price, boolean hasAutopilot, int batteryCapacity) {
        super("Tesla",model, year, color, horsepower, isAutomatic, price);
        this.hasAutopilot = hasAutopilot;
        this.batteryCapacity = batteryCapacity;
    }

    public boolean isHasAutopilot() {
        return hasAutopilot;
    }

    public void setHasAutopilot(boolean hasAutopilot) {
        this.hasAutopilot = hasAutopilot;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
