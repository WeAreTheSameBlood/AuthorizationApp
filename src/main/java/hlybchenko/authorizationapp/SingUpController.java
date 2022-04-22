package hlybchenko.authorizationapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.util.Random;

public class SingUpController extends AuthorizationSceneController{

    @FXML
    private Button singUpButton;

    @FXML
    private Button singUpButtonBack;

    @FXML
    private TextField singUpFirstName;

    @FXML
    private CheckBox singUpGenderFemale;

    @FXML
    private CheckBox singUpGenderMale;

    @FXML
    private Label singUpGeneralError;

    @FXML
    private Button singUpGeneratePassButton;

    @FXML
    private TextField singUpLastName;

    @FXML
    private TextField singUpLocation;

    @FXML
    private TextField singUpLogin;

    @FXML
    private Label singUpPasswordError;

    @FXML
    private TextField singUpPassword;

    @FXML
    void initialize() {
        singUpGenderMale.setOnAction(event -> genderTest(singUpGenderFemale, singUpGenderMale));
        singUpGenderFemale.setOnAction(event -> genderTest(singUpGenderMale,singUpGenderFemale));
        singUpButton.setOnAction(actionEvent -> singUpNewUser());
        singUpButtonBack.setOnAction(event -> scene("authorizationScene.fxml", singUpButtonBack));
        singUpGeneratePassButton.setOnAction(event -> singUpPassword.setText(passGenerator(8)));
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
    public String passGenerator(int length){
        Random random = new Random();
        return random.ints(46, 122 + 1)   // leftLimit = 46 - numeral '.' rightLimit = 122 - letter 'z'
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) //without ':', ';', '<', '=', '>', '?', '@', '[', '\', ']', '^', '_', '`' https://unicode-table.com/ru/#0060
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}