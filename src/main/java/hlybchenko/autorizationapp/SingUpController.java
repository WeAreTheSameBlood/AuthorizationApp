package hlybchenko.autorizationapp;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private Label singUpPasswordError;

    @FXML
    public Label singUpGeneralError;

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
        String firstName = singUpFirstName.getText().trim();
        String lastName = singUpLastName.getText().trim();
        String login = singUpLogin.getText().trim();
        String password = singUpPassword.getText().trim();
        if (singUpPassword.getText().trim().length() < 6) singUpPasswordError.setText("weak password (minimum 6 characters)");
        else singUpPasswordError.setText("");
        String location = singUpLocation.getText().trim();
        String gender;
        if (singUpGenderMale.isSelected()) gender = "Male";
        else gender = "Female";
        User user = new User(firstName, lastName, login, password, location, gender);
        try {
            dbHandler.singUpUser(user);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String[] userData = {firstName, lastName, login, location};
        for (String s : userData) {
            if (s.length() < 1) singUpGeneralError.setText("Error: all fields must be filled.");
            else singUpGeneralError.setText("");
        }
    }
}