package hlybchenko.autorizationapp;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SingUpController extends AuthorizationSceneController{

    @FXML
    private Button singUpButtonBack;

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
        singUpGenderMale.setOnAction(event -> genderTest(singUpGenderFemale, singUpGenderMale));
        singUpGenderFemale.setOnAction(event -> genderTest(singUpGenderMale,singUpGenderFemale));
        singUpButton.setOnAction(actionEvent -> singUpNewUser());
        singUpButtonBack.setOnAction(event -> {
            singUpButtonBack.getScene().getWindow().hide();
            scene("authorizationScene.fxml");
        });
    }

    private void genderTest(CheckBox cB1, CheckBox cB2) { if (cB2.isSelected()) cB1.setSelected(false); }

    private void singUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String firstName = singUpFirstName.getText();
        String lastName = singUpLastName.getText();
        String login = singUpLogin.getText();
        String password = singUpPassword.getText();
        String location = singUpLocation.getText();
        String gender;
        if (singUpGenderMale.isSelected()) gender = "Male";
        else gender = "Female";
        User user = new User(firstName, lastName, login, password, location, gender);
        try {
            dbHandler.singUpUser(user);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}