package hlybchenko.autorizationapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfulLoginSceneController extends AuthorizationSceneController {
    @FXML
    private Button succLoginButtonBack;

    @FXML
    void initialize() {
        succLoginButtonBack.setOnAction(event -> {
            succLoginButtonBack.getScene().getWindow().hide();
            scene("authorizationScene.fxml");
        });
    }
}