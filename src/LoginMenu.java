import java.util.Scanner;

public class LoginMenu {
    private Scanner scanner;

    public LoginMenu() {
        scanner = new Scanner(System.in);
    }

    public void displayLogin() {
        boolean loginSuccessful = false;
        while (!loginSuccessful) {
            System.out.println("=========== Menu Login ===========");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("Login successful!");
                DetailsMenu detailsMenu = new DetailsMenu();
                detailsMenu.showMenu();
                loginSuccessful = true;
            } else {
                System.out.println("Wrong username or password!");
            }
        }
    }
}