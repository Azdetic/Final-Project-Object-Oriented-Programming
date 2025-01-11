import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Car {
    private String plateNumber;
    private String color;
    private String brand;
    private String timeIn;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
        try {
            LocalDateTime timeInLocal = LocalDateTime.parse(timeIn, formatter);
            LocalDateTime now = LocalDateTime.now();
            long minutesBetween = ChronoUnit.MINUTES.between(timeInLocal, now);
            return (minutesBetween < 5) ? 0 : (minutesBetween / 5) * 2000;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return 0;
        }
    }
}
