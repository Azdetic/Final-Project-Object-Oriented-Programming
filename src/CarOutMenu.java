import java.util.Scanner;

public class CarOutMenu {
    private Scanner scanner;
    private DetailsMenu detailsMenu;
    private DetailsParking detailsParking;
    private MemberDataMenu memberDataMenu;

    public CarOutMenu(DetailsMenu detailsMenu, DetailsParking detailsParking, MemberDataMenu memberDataMenu) {
        this.scanner = new Scanner(System.in);
        this.detailsMenu = detailsMenu;
        this.detailsParking = detailsParking;
        this.memberDataMenu = memberDataMenu;
    }

    public void show() {
        String response;
        try {
            String plateNumber;
            DetailsParking.Car car;
            do {
                System.out.println("===== Car Out =====");
                do {
                    System.out.print("Input Plate: ");
                    plateNumber = scanner.nextLine();
                    if (plateNumber.trim().isEmpty()) {
                        System.out.println("Do not leave blank data, it cannot be processed.");
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
                            if (response.equalsIgnoreCase("y")) {
                                validMember = true;
                                // Process payment and remove car from parking
                                System.out.println("Payment successful. Car removed from parking.");
                                return; // Direct to Main Menu
                            } else if (response.equalsIgnoreCase("n")) {
                                System.out.println("Please re-enter the member phone number.");
                            } else {
                                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                            }
                        } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
                    } else {
                        System.out.println("Member not found. Please try again.");
                    }
                } while (!validMember);
            } else {
                // Process payment and remove car from parking without member
                if (detailsMenu.getUserBalance() >= currentPrice) {
                    detailsMenu.setUserBalance(detailsMenu.getUserBalance() - currentPrice);
                    System.out.println("Payment successful. Car removed from parking.");
                    System.out.println("Remaining balance: " + detailsMenu.getUserBalance() + " IDR");
                } else {
                    System.out.println("Insufficient balance. Payment failed.");
                }
                return; // Direct to Main Menu
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
