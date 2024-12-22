import java.util.Scanner;

public class LoginMenu {
    private Scanner scanner;
    private double userBalance;

    public LoginMenu() {
        scanner = new Scanner(System.in);
    }

    public void displayLogin() {
        boolean loginSuccessful = false;
        while (!loginSuccessful) {
            try {
                System.out.println("=========== Menu Login ===========");
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                if (username.equals("admin") && password.equals("admin")) {
                    System.out.print("Enter your balance: ");
                    userBalance = Double.parseDouble(scanner.nextLine());
                    System.out.println("Login successful!");
                    DetailsMenu detailsMenu = new DetailsMenu(userBalance);
                    detailsMenu.showMenu();
                    loginSuccessful = true;
                } else {
                    System.out.println("Wrong username or password!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid balance input. Please enter a valid number");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public double getUserBalance() {
        return userBalance;
    }
}