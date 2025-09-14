package Cars;

public abstract class Car {
    private String brand;
    private String model;
    private int year;
    private String color;
    private int horsepower;
    private boolean isAutomatic;
    private double price;

    public Car(String brand, String model, int year, String color, int horsepower, boolean isAutomatic, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.horsepower = horsepower;
        this.isAutomatic = isAutomatic;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car " +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", horsepower=" + horsepower +
                ", isAutomatic=" + isAutomatic +
                ", price=" + price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
