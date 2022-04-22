package hlybchenko.authorizationapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfulLoginSceneController extends AuthorizationSceneController {
    @FXML
    private Button succLoginButtonBack;

    @FXML
    void initialize() {
        succLoginButtonBack.setOnAction(event -> scene("authorizationScene.fxml", succLoginButtonBack));
    }
}