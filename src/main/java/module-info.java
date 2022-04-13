module hlybchenko.autorizationapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens hlybchenko.autorizationapp to javafx.fxml;
    exports hlybchenko.autorizationapp;
}