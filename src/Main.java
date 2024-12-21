public class Main {
    public static void main(String[] args) {
        try {
            LoginMenu loginMenu = new LoginMenu();
            loginMenu.displayLogin();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}