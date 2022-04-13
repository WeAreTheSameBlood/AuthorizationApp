package hlybchenko.autorizationapp;

import java.net.URL;
import java.sql.SQLException;
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
        DatabaseHandler dbHandler = new DatabaseHandler();
        singUpButton.setOnAction(actionEvent -> {
            try {
                dbHandler.singUpUser(singUpFirstName.getText(), singUpLastName.getText(), singUpLogin.getText(), singUpPassword.getText(), singUpLocation.getText(), "Male");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

}