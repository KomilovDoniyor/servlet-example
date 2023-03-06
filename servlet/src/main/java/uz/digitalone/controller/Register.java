package uz.digitalone.controller;


import uz.digitalone.model.Results;
import uz.digitalone.model.Users;
import uz.digitalone.repository.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String retype_password = req.getParameter("retype_password");
        PrintWriter out = resp.getWriter();
        if (password.equals(retype_password)){
            DatabaseConnection connection = new DatabaseConnection();
            Results result = null;
            try {
                result = connection.register(new Users(
                        firstname,
                        lastname,
                        email,
                        password
                ));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            assert result != null;
            if(result.isSuccess()){
                out.write("<h1 style='color:green;'>Successfully registered</h1");
            }
        }else {
            resp.setContentType("text/html");
            out.write("<h1 style='color:red;'>Something went wrong</h1");
        }
    }
}
