import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMenu {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private void onclicklogin() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (!authenticate(username, password)) {
            errorMessageLabel.setText("Invalid username or password. Please try again.");
        } else {
            errorMessageLabel.setText("");
            loadDetailsMenu();
        }
    }

    private boolean authenticate(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
    }

    private void loadDetailsMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            errorMessageLabel.setText("Failed to load the details view.");
        }
    }
}
