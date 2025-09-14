import Cars.*;

import java.util.ArrayList;
import java.util.List;

public class AddInfo {
    public static List<Car> addCars() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Tesla("Model S", 2023, "Black", 670, true, 74990.0, true, 100));
        cars.add(new BMW("X5", 2022, "White", 335, true, 60500.0));
        cars.add(new Jeep("Wrangler", 2023, "Green", 285, false, 39000.0));
        cars.add(new Suzuki("Swift", 2022, "Blue", 120, true, 15000.0));
        cars.add(new Toyota("Camry", 2021, "Silver", 203, true, 26500.0));
        cars.add(new Tesla("Model 3", 2023, "Gray", 450, true, 40000.0, false, 75));
        cars.add(new BMW("M3", 2023, "Red", 500, true, 75000.0));
        cars.add(new Jeep("Grand Cherokee", 2022, "Black", 360, true, 45000.0));
        cars.add(new Suzuki("Vitara", 2021, "Yellow", 140, true, 22000.0));
        cars.add(new Toyota("RAV4", 2023, "White", 203, true, 30000.0));
        cars.add(new BMW("3 Series", 2005, "Blue", 190, false, 8000.0));
        cars.add(new Toyota("Corolla", 2004, "Green", 130, false, 5500.0));
        cars.add(new Suzuki("Grand Vitara", 2003, "Red", 160, false, 7000.0));
        cars.add(new Jeep("Liberty", 2006, "Green", 210, true, 9000.0));
        cars.add(new Tesla("Model Y", 2023, "White", 380, true, 35000.0, true, 75));

        return cars;
    }
}