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
            Car car;  // Change from DetailsParking.Car to Car
            do {
                System.out.println("===== Car Out =====");
                do {
                    System.out.print("Input Plate: ");
                    plateNumber = scanner.nextLine();
                    if (plateNumber.trim().isEmpty()) {
                        System.out.println("Do not leave blank data, it cannot be processed");
                        return;
                    }
                } while (plateNumber.trim().isEmpty());

                car = detailsParking.findCarByPlateNumber(plateNumber);
                if (car == null) {
                    System.out.println("Data not found, please enter new data");
                    do {
                        System.out.print("Want to check plate number in parking details? (y/n): ");
                        response = scanner.nextLine();
                        if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
                            System.out.println("Invalid choice. Please enter 'y' or 'n'");
                        }
                    } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));

                    if (response.equalsIgnoreCase("y")) {
                        detailsParking.checkParkingData();
                    }
                }
            } while (car == null);

            long currentPrice = car.calculateCurrentPrice();
            System.out.println("Current Price: " + currentPrice + " IDR | Using member discount 50%: " + (currentPrice * 0.5) + " IDR");

            do {
                System.out.print("Payment using member? (y/n): ");
                response = scanner.nextLine();
                if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
                    System.out.println("Invalid choice. Please enter 'y' or 'n'");
                }
            } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));

            if (response.equalsIgnoreCase("y")) {
                boolean validMember = false;
                do {
                    System.out.print("Input Member Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    Member member = memberDataMenu.findMemberByPhoneNumber(phoneNumber);
                    if (member != null) {
                        System.out.println("Member found: " + member);
                        System.out.println("Current Balance: " + detailsMenu.getUserBalance() + " IDR");
                        System.out.println("Total Price discount 50%: " + (currentPrice * 0.5) + " IDR");
                        do {
                            System.out.print("Is this information correct? (y/n): ");
                            response = scanner.nextLine();
                            if (response.equalsIgnoreCase("y")) {
                                validMember = true;
                                //check balance and subtract the discounted price
                                long discountedPrice = (long)(currentPrice * 0.5);
                                if (detailsMenu.getUserBalance() >= discountedPrice) {
                                    detailsMenu.setUserBalance(detailsMenu.getUserBalance() - discountedPrice);
                                    detailsParking.removeCar(car);
                                    System.out.println("Payment successful. Car removed from parking");
                                    System.out.println("Remaining balance: " + detailsMenu.getUserBalance() + " IDR");
                                    return;
                                } else {
                                    System.out.println("Insufficient balance. Payment failed");
                                }
                            } else if (response.equalsIgnoreCase("n")) {
                                System.out.println("Please re-enter the member phone number");
                            } else {
                                System.out.println("Invalid choice. Please enter 'y' or 'n'");
                            }
                        } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
                    } else {
                        System.out.println("Member not found. Please try again");
                    }
                } while (!validMember);
            } else {
                //process payment and remove car from parking without member
                System.out.println("Current Balance: " + detailsMenu.getUserBalance() + " IDR");
                System.out.println("Total Price: " + currentPrice + " IDR");
                do {
                    System.out.print("Do you want to make member first? (y/n): ");
                    response = scanner.nextLine();
                    if (response.equalsIgnoreCase("y")) {
                        //logic to create a new member
                        System.out.println("Please proceed to create a new member");
                        //add member creation logic here
                    } else if (response.equalsIgnoreCase("n")) {
                        if (detailsMenu.getUserBalance() >= currentPrice) {
                            detailsMenu.setUserBalance(detailsMenu.getUserBalance() - currentPrice);
                            detailsParking.removeCar(car); //add this line to remove the car
                            System.out.println("Payment successful. Car removed from parking");
                            System.out.println("Remaining balance: " + detailsMenu.getUserBalance() + " IDR");
                        } else {
                            System.out.println("Insufficient balance. Payment failed");
                        }
                        return; //direct to Main Menu
                    } else {
                        System.out.println("Invalid choice. Please enter 'y' or 'n'");
                    }
                } while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
