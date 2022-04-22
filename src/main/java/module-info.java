module hlybchenko.autorizationapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens hlybchenko.authorizationapp to javafx.fxml;
    exports hlybchenko.authorizationapp;
}