import java.util.Scanner;

public class DetailsMenu {
    private Scanner scanner;
    private DetailsParking detailsParking;
    private MemberDataMenu memberDataMenu;
    private double userBalance;

    public DetailsMenu(double userBalance) {
        this.userBalance = userBalance;
        this.scanner = new Scanner(System.in);
        this.detailsParking = new DetailsParking();
        this.memberDataMenu = new MemberDataMenu();
    }

    public void showMenu() {
        int choice = -1;
        while (choice != 0) {
            try {
                System.out.println("=========== Main Menu ===========");
                System.out.println("1. Car In");
                System.out.println("2. Car Out");
                System.out.println("3. Parking Details");
                System.out.println("4. Member Data");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); //consume newline left-over
                } else {
                    System.out.println("Invalid choice, choose 0-4:");
                    scanner.next(); //consume invalid input
                    continue;
                }

                switch (choice) {
                    case 1:
                        CarInMenu carInMenu = new CarInMenu(detailsParking, this);
                        carInMenu.show();
                        break;
                    case 2:
                        CarOutMenu carOutMenu = new CarOutMenu(this, detailsParking, memberDataMenu);
                        carOutMenu.show();
                        break;
                    case 3:
                        detailsParking.checkParkingData();
                        break;
                    case 4:
                        memberDataMenu.show();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice, choose 0-4:");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                choice = 0; //exit the loop in case of an error
            }
        }
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }
}