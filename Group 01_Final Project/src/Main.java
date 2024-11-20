import java.sql.*;
import java.util.*;
import java.util.regex.*;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private static Connection conn;

    public static void main(String[] args) {
        try {
            conn = FirstMenu.getConnection();
            createTables();
            showFirstMenu();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createTables() {
        String createUserAdmin = """
            CREATE TABLE IF NOT EXISTS UserAdmin (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL
            )
        """;

        String createVerificationCodes = """
            CREATE TABLE IF NOT EXISTS VerificationCodes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                code TEXT UNIQUE NOT NULL,
                used INTEGER DEFAULT 0
            )
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createUserAdmin);
            stmt.execute(createVerificationCodes);
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    private static void showFirstMenu() {
        while (true) {
            System.out.println("\nFIRST MENU");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter number 1-3 = ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (choice) {
                    case 1:
                        showAdminMenu();
                        break;
                    case 2:
                        System.out.println("Customer menu (to be implemented)");
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        if (conn != null) {
                            conn.close();
                        }
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (SQLException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    private static void showAdminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Create code");
            System.out.println("4. Back to main menu");
            System.out.print("Enter choice: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (choice) {
                    case 1 -> adminLogin();
                    case 2 -> adminRegister();
                    case 3 -> createVerificationCode();
                    case 4 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    private static void adminLogin() {
        System.out.println("\nLogin Menu");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            String query = "SELECT * FROM UserAdmin WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful!");
                showControlMenu();
            } else {
                System.out.println("Username/password salah silahkan input kembali");
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    private static void adminRegister() {
        System.out.println("\nRegister Menu");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Verif code: ");
        String verifCode = scanner.nextLine();

        if (username.trim().isEmpty()) {
            System.out.println("Username tidak boleh blank!");
            return;
        }

        if (!isPasswordValid(password)) {
            System.out.println("Password harus berisi huruf besar, huruf kecil, dan angka!");
            return;
        }

        if (!isVerificationCodeValid(verifCode)) {
            System.out.println("Verify code salah!");
            return;
        }

        try {
            String query = "INSERT INTO UserAdmin (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            // Mark verification code as used
            query = "UPDATE VerificationCodes SET used = 1 WHERE code = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, verifCode);
            pstmt.executeUpdate();

            System.out.println("Registration successful!");
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE")) {
                System.out.println("Username already exists!");
            } else {
                System.out.println("Error during registration: " + e.getMessage());
            }
        }
    }

    private static boolean isPasswordValid(String password) {
        // Password must contain at least one uppercase letter, one lowercase letter, and one number
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
        return Pattern.matches(regex, password);
    }

    private static boolean isVerificationCodeValid(String code) {
        try {
            String query = "SELECT * FROM VerificationCodes WHERE code = ? AND used = 0";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking verification code: " + e.getMessage());
            return false;
        }
    }

    private static void createVerificationCode() {
        String code = generateRandomCode();
        try {
            String query = "INSERT INTO VerificationCodes (code) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, code);
            pstmt.executeUpdate();
            System.out.println("Code berhasil terbuat: " + code);
        } catch (SQLException e) {
            System.out.println("Error creating verification code: " + e.getMessage());
        }
    }

    private static String generateRandomCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    private static void showControlMenu() {
        System.out.println("\nControl Menu");
        // Add control menu functionality here
    }
}