import java.util.Scanner;

public class AdminMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            // Menampilkan menu Admin
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Show Data Parking");
            System.out.println("2. Show Data Car Missing");
            System.out.println("3. Exit Role Admin");
            System.out.print("Please select an option (1-3): ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showParkingData();
                    break;

                case 2:
                    showMissingCarData();
                    break;

                case 3:
                    System.out.println("Exiting Role Admin...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showParkingData() {
        System.out.println("\n--- Data Parking ---");
    }

    private static void showMissingCarData() {
        System.out.println("\n--- Data Car Missing ---");
    }
}
