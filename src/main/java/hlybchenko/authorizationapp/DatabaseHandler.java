package hlybchenko.authorizationapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void singUpUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE.getValue() + "(" + Const.USERS_FIRSTNAME.getValue() + ","
                + Const.USERS_LASTNAME.getValue() + "," + Const.USERS_USERNAME.getValue() + "," + Const.USERS_PASSWORD.getValue()
                + "," + Const.USERS_LOCATION.getValue() + "," + Const.USERS_GENDER.getValue() + ")" + "VALUES(?,?,?,?,?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, user.getFirstName());
        prSt.setString(2, user.getLastName());
        prSt.setString(3, user.getLogin());
        prSt.setString(4, user.getPassword());
        prSt.setString(5, user.getLocation());
        prSt.setString(6, user.getGender());
        if (user.getFirstName().length() >= 1 &&
                user.getLastName().length() >= 1 &&
                user.getLogin().length() >= 1 &&
                user.getLocation().length() >= 1 &&
                user.getPassword().length() >= 6) prSt.executeUpdate();
    }

    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet;
        String select = "SELECT * FROM " + Const.USER_TABLE.getValue() + " WHERE " + Const.USERS_USERNAME.getValue() + "=? AND " + Const.USERS_PASSWORD.getValue() + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, user.getLogin());
        prSt.setString(2, user.getPassword());
        resultSet = prSt.executeQuery();
        return resultSet;
    }
}