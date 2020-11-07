package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static final String hostName = "localhost";
    private static final String dbName = "my_schema";
    private static final String userName = "root";
    private static final String password = "root";

    public static Connection getConnection() {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=Asia/Yekaterinburg&useSSL=false";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}


