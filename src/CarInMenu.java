import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CarInMenu {
    private Scanner scanner;
    private DetailsParking detailsParking;
    private DetailsMenu detailsMenu;

    public CarInMenu(DetailsParking detailsParking, DetailsMenu detailsMenu) {
        this.scanner = new Scanner(System.in);
        this.detailsParking = detailsParking;
        this.detailsMenu = detailsMenu;
    }

    public void show() {
        String response;
        do {
            try {
                System.out.println("=========== Car In ===========");
                System.out.println("Current Time: " + getCurrentDateTime());

                String plateNumber;
                do {
                    System.out.print("Plate Number: ");
                    plateNumber = scanner.nextLine();
                    if (plateNumber.trim().isEmpty()) {
                        System.out.println("Do not blank data cannot be processed");
                        return;
                    } else if (detailsParking.findCarByPlateNumber(plateNumber) != null) {
                        System.out.println("Plate number already exists in the parking lot");
                        return;
                    }
                } while (plateNumber.trim().isEmpty());

                String carColor;
                do {
                    System.out.print("Car Color: ");
                    carColor = scanner.nextLine();
                    if (carColor.trim().isEmpty()) {
                        System.out.println("Do not blank data cannot be processed");
                        return;
                    }
                } while (carColor.trim().isEmpty());

                String carBrand;
                do {
                    System.out.print("Car Brand: ");
                    carBrand = scanner.nextLine();
                    if (carBrand.trim().isEmpty()) {
                        System.out.println("Do not blank data cannot be processed");
                        return;
                    }
                } while (carBrand.trim().isEmpty());

                if (detailsParking.isParkingFull()) {
                    System.out.println("Parking lot is full. Cannot add more cars.");
                    return;
                }

                String timeIn = getCurrentDateTime();
                detailsParking.addCar(plateNumber, carColor, carBrand, timeIn);
                System.out.println("Car added: " + plateNumber + ", " + carColor + ", " + carBrand);

                do {
                    System.out.print("Want to add more car? (y/n): ");
                    response = scanner.nextLine();
                    if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
                        System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                    }
                } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                return;
            }
        } while (response.equalsIgnoreCase("y"));
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}