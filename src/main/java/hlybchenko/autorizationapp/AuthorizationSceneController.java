package hlybchenko.autorizationapp;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
    private ResourceBundle resources;

    @FXML
    private URL location;

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
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                authErrorMessage.setText("Error: login or/and password failed.");
                authLoginTextField.undo();
                authPasswordField.undo();
            }
        });

        singUpButton.setOnAction(actionEvent -> {
                    singUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("singUp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.showAndWait();
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
            System.out.println("Success!");
        }
    }
}