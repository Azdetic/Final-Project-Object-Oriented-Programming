import java.util.Scanner;

public class CustomerMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            // Menampilkan menu
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Car In");
            System.out.println("2. Car Out");
            System.out.println("3. Exit Role Customer");
            System.out.print("Please select an option (1-3): ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 ->
                    System.out.println("Car has entered the parking lot");

                case 2 ->
                    System.out.println("Car has left the parking lot");

                case 3 -> {
                    System.out.println("Exiting Role Customer...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
