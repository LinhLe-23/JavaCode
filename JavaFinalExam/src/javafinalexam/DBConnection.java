package javafinalexam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/VboxData";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "231118";

    public static Connection getConnection() {
        Connection connect = null;

        try {
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("KET NOI THANH CONG");
        } catch (SQLException e) {
            System.out.println("KET NOI THAT BAI");
            e.printStackTrace();
        }

        return connect;
    }
}
