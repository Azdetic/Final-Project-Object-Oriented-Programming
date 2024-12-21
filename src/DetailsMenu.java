import java.util.Scanner;

public class DetailsMenu {
    private Scanner scanner;
    private DetailsParking detailsParking;
    private MemberDataMenu memberDataMenu;

    public DetailsMenu() {
        scanner = new Scanner(System.in);
        detailsParking = new DetailsParking();
        memberDataMenu = new MemberDataMenu();
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
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                switch (choice) {
                    case 1:
                        CarInMenu carInMenu = new CarInMenu(detailsParking, this);
                        carInMenu.show();
                        break;
                    case 2:
                        CarOutMenu carOutMenu = new CarOutMenu(detailsParking, memberDataMenu, this);
                        carOutMenu.show();
                        break;
                    case 3:
                        detailsParking.checkParkingData();
                        break;
                    case 4:
                        memberDataMenu.show();
                        break;
                    default:
                        System.out.println("Invalid choice, choose 0-4:");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                choice = 0; // Exit the loop in case of an error
            }
        }
    }
}