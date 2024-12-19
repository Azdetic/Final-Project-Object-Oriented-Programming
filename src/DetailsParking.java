import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DetailsParking {
    private List<Car> parkedCars; // A list to hold parked car information
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Scanner scanner;

    public DetailsParking() {
        this.parkedCars = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        // Sample data, you would normally load this from your data storage
        parkedCars.add(new Car("AB123CD", "Red", "Toyota", "2023-12-19 12:34:56"));
        parkedCars.add(new Car("XY789ZT", "Blue", "Honda", "2023-12-19 12:50:01"));
    }

    public void displayCurrentParking() {
        System.out.println("=========== Details Current Parking ===========");
        int count = 1;
        for (Car car : parkedCars) {
            System.out.println("==== " + count + " ====");
            System.out.println("Plate Number: " + car.getPlateNumber());
            System.out.println("Car Color: " + car.getColor());
            System.out.println("Car Brand: " + car.getBrand());
            System.out.println("Time In: " + car.getTimeIn());
            System.out.println("Current Price: " + car.calculateCurrentPrice() + " IDR");
            count++;
        }
    }

    public void checkParkingData() {
        String response;
        do {
            displayCurrentParking();
            do {
                System.out.print("Still want to check data? (y/n): ");
                response = scanner.nextLine();
                if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                }
            } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
        } while (response.equalsIgnoreCase("y"));
    }

    public void addCar(String plateNumber, String color, String brand, String timeIn) {
        parkedCars.add(new Car(plateNumber, color, brand, timeIn));
    }

    public Car findCarByPlateNumber(String plateNumber) {
        for (Car car : parkedCars) {
            if (car.getPlateNumber().equals(plateNumber)) {
                return car;
            }
        }
        return null;
    }

    public void removeCar(String plateNumber) {
        parkedCars.removeIf(car -> car.getPlateNumber().equals(plateNumber));
    }

    public class Car {
        private String plateNumber;
        private String color;
        private String brand;
        private String timeIn;

        public Car(String plateNumber, String color, String brand, String timeIn) {
            this.plateNumber = plateNumber;
            this.color = color;
            this.brand = brand;
            this.timeIn = timeIn;
        }

        public String getPlateNumber() {
            return plateNumber;
        }

        public String getColor() {
            return color;
        }

        public String getBrand() {
            return brand;
        }

        public String getTimeIn() {
            return timeIn;
        }

        public long calculateCurrentPrice() {
            LocalDateTime timeInLocal = LocalDateTime.parse(timeIn, formatter);
            LocalDateTime now = LocalDateTime.now();
            long minutesBetween = ChronoUnit.MINUTES.between(timeInLocal, now);
            return (minutesBetween < 5) ? 0 : (minutesBetween / 5) * 2000; // No charge for less than 5 minutes
        }
    }
}