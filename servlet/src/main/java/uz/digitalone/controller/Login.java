package uz.digitalone.controller;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static uz.digitalone.repository.DatabaseConnection.connection;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from users where email = '" + email + "' and password = '" + password + "'");
            if (resultSet.next()){
                resp.sendRedirect("home.html");
            }else {
                PrintWriter out = resp.getWriter();
                resp.setContentType("text/html");
                out.write("<h1 style='color:red;'>Wrong email and password</h1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
