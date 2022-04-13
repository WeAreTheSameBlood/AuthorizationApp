package hlybchenko.autorizationapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthorizationSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField authLoginTextField;

    @FXML
    private PasswordField authPasswordField;

    @FXML
    private Button singInButton;

    @FXML
    private Button singUpButton;

    @FXML
    void initialize() {

    }

}