package com.demo.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registration")
public class Registration extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("emailId");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        System.out.println(name);
        System.out.println(department);
        System.out.println(mobile);
        System.out.println(email);
        System.out.println(gender);
        System.out.println(password);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/registration", "root", "root");

            Statement stmnt = con.createStatement();

            stmnt.executeUpdate(
                "INSERT INTO user(name, department, mobile, email, gender, password) " +
                "VALUES ('"+name+"','"+department+"','"+mobile+"','"+email+"','"+gender+"','"+password+"')"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
