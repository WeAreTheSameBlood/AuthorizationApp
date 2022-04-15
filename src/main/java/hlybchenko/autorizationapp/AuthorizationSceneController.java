package hlybchenko.autorizationapp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import hlybchenko.animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthorizationSceneController {

    @FXML
    private Label authErrorMessage;

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
        singInButton.setOnAction(actionEvent -> {
            String loginText = authLoginTextField.getText().trim();
            String loginPassword = authPasswordField.getText().trim();
            if (!loginText.equals("") && !loginPassword.equals("")){
                try {
                    loginUser(loginText, loginPassword);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                errorAndShake();
                authLoginTextField.undo();
                authPasswordField.undo();
            }
        });

        singUpButton.setOnAction(actionEvent -> {
                    singUpButton.getScene().getWindow().hide();
                    scene("singUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        while (result.next()) {
            counter++;
        }
        if (counter >= 1) {
            singInButton.getScene().getWindow().hide();
            scene("successfulLogin.fxml");
        } else { errorAndShake();}
    }

    private void errorAndShake() {
        Shake authLoginAnim = new Shake(authLoginTextField);
        Shake authPassAnim = new Shake(authPasswordField);
        authLoginAnim.playAnim();
        authPassAnim.playAnim();
        authErrorMessage.setText("Error: login or/and password failed.");
    }

    public void scene(String sceneFXML){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(sceneFXML));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(parent));
        stage.show();
    }
}