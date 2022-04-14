package hlybchenko.autorizationapp;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button singUpButton;

    @FXML
    private TextField singUpFirstName;

    @FXML
    private CheckBox singUpGenderFemale;

    @FXML
    private CheckBox singUpGenderMale;

    @FXML
    private TextField singUpLastName;

    @FXML
    private TextField singUpLocation;

    @FXML
    private TextField singUpLogin;

    @FXML
    private PasswordField singUpPassword;

    @FXML
    void initialize() {

        singUpButton.setOnAction(actionEvent -> {
            singUpNewUser();
        });
    }

    private void singUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String firstName = singUpFirstName.getText();
        String lastName = singUpLastName.getText();
        String login = singUpLogin.getText();
        String password = singUpPassword.getText();
        String location = singUpLocation.getText();
        String gender = "";
        if (singUpGenderMale.isSelected()) gender = "Male";
        else gender = "Female";
        User user = new User(firstName, lastName, login, password, location, gender);
        try {
            dbHandler.singUpUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}