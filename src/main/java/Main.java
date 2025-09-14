import Cars.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    // it's Main origin
    public static void main(String[] args) {
        List<Car> cars = AddInfo.addCars();

        System.out.println("Список всех автомобилей: ");
        int i=1;
        for (Car car : cars) {
            System.out.println(i++ +" "+car);
        }

        infoCarAfter2006(cars);
        repaintFromGreenToRed(cars);
        filterPrice(true,cars);
        hasAutopilot(cars);
    }

    private static void infoCarAfter2006(List<Car> cars){
        System.out.println("\nАвтомобиле выпущенные после 2006: ");
        int i=1;
        for (Car car : cars) {
            if(car.getYear()>2006){
                System.out.println(i++ +" "+car);
            }
        }
    }

    private static void repaintFromGreenToRed(List<Car> cars){
        System.out.println("\nПерекраска автомобилей из зеленого а красный:");
        int i=1;
        for (Car car : cars) {
            if(car.getColor().equals("Green")){
                car.setColor("Red");
                System.out.println(i++ +" "+car);
            }
        }
    }

    private static void filterPrice(boolean reverse, List<Car> cars){
        List<Car> sorted = new ArrayList<>(cars);
        System.out.println("\nОтсортированный список автомобилей: ");
        if (reverse){
            System.out.println("От большего к меньшему");
            sorted.sort(Comparator.comparing(Car::getPrice).reversed());
        }
        else {
            System.out.println("От меньшего к большему");
            sorted.sort(Comparator.comparing(Car::getPrice));
        }

        int i=1;
        for (Car car : sorted) {
            System.out.println(i++ +" "+car);
        }
    }

    private static void hasAutopilot(List<Car> cars){
        System.out.println("\nМашины с автопилотом:");
        int i=1;
        for (Car car : cars) {
            if (car instanceof Tesla && ((Tesla) car).isHasAutopilot()){
                System.out.println(i++ +" "+car);
            }
        }
    }
}
