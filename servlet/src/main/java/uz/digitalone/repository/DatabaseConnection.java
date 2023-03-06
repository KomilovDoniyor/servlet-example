package uz.digitalone.repository;

import uz.digitalone.model.Results;
import uz.digitalone.model.Users;


import java.sql.*;

public class DatabaseConnection {
    private final String url = "jdbc:postgresql://localhost:5432/servlet_example_db";
    private final String username = "postgres";
    private final String password = "25052001Dm.";

    public static Connection connection;

    static {
        try {
            connection = DatabaseConnection.connect("servlet_example_db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect(String dbName) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/servlet_example_db";
        String username = "postgres";
        String password = "25052001Dm.";
        return DriverManager.getConnection(url, username, password);
    }

    public Results register(Users user) throws ClassNotFoundException {
        Connection conn = null;
        Class.forName("org.postgresql.Driver");

        try {
            conn = DriverManager.getConnection(url, username, password);
            String INSERT_NEW_USER = "insert into users(firstname, lastname, email, password) values(?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_NEW_USER);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                return new Results(true, "Successfully saved");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Results(false, "Something went wrong");
    }

}
