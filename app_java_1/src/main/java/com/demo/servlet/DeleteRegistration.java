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

@WebServlet("/DeleteRegistration")
public class DeleteRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
        String department = request.getParameter("department");
        String email = request.getParameter("emailId");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        System.out.println(department);
        System.out.println(email);
        System.out.println(gender);
        System.out.println(password);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/registration", "root", "root");

            Statement stmnt = con.createStatement();

            stmnt.executeUpdate("Delete from user where email='"+email+"'");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
