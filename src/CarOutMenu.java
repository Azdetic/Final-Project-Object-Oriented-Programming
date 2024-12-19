import java.util.Scanner;

public class CarOutMenu {
    private Scanner scanner;
    private DetailsParking detailsParking;
    private DetailsMenu detailsMenu;
    private MemberDataMenu memberDataMenu;

    public CarOutMenu(DetailsParking detailsParking, MemberDataMenu memberDataMenu, DetailsMenu detailsMenu) {
        this.scanner = new Scanner(System.in);
        this.detailsParking = detailsParking;
        this.memberDataMenu = memberDataMenu;
        this.detailsMenu = detailsMenu;
    }

    public void show() {
        String response;
        do {
            String plateNumber;
            DetailsParking.Car car;
            do {
                System.out.println("===== Car Out =====");
                do {
                    System.out.print("Input Plate: ");
                    plateNumber = scanner.nextLine();
                    if (plateNumber.trim().isEmpty()) {
                        System.out.println("do not blank data cannot be processed");
                        detailsMenu.showMenu();
                        return;
                    }
                } while (plateNumber.trim().isEmpty());

                car = detailsParking.findCarByPlateNumber(plateNumber);
                if (car == null) {
                    System.out.println("Data not found, please enter new data.");
                    do {
                        System.out.print("Want to check plate number in parking details? (y/n): ");
                        response = scanner.nextLine();
                        if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
                            System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                        }
                    } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));

                    if (response.equalsIgnoreCase("y")) {
                        detailsParking.checkParkingData();
                    }
                }
            } while (car == null);

            long currentPrice = car.calculateCurrentPrice();
            System.out.println("Current Price: " + currentPrice + " IDR");

            do {
                System.out.print("Payment using member? (y/n): ");
                response = scanner.nextLine();
                if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                }
            } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));

            if (response.equalsIgnoreCase("y")) {
                boolean validMember = false;
                do {
                    System.out.print("Input Member Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    MemberDataMenu.Member member = memberDataMenu.findMemberByPhoneNumber(phoneNumber);
                    if (member != null) {
                        System.out.println("Member found: " + member);
                        do {
                            System.out.print("Is this information correct? (y/n): ");
                            response = scanner.nextLine();
                            if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
                                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                            }
                        } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));

                        if (response.equalsIgnoreCase("y")) {
                            currentPrice = currentPrice / 2; // Apply 50% discount
                            System.out.println("Discounted Price: " + currentPrice + " IDR");
                            validMember = true;
                        }
                    } else {
                        System.out.println("PhoneNumber don't have any Member");
                    }
                } while (!validMember);
            }

            System.out.println("Payment successful. Car removed from parking.");
            detailsParking.removeCar(plateNumber);

            do {
                System.out.print("Want to remove another car? (y/n): ");
                response = scanner.nextLine();
                if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                }
            } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
        } while (response.equalsIgnoreCase("y"));
    }
}
