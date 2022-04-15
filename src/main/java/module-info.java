module hlybchenko.autorizationapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens hlybchenko.autorizationapp to javafx.fxml;
    exports hlybchenko.autorizationapp;
}