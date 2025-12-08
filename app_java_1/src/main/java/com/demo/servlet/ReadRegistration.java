package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//write data from servlet to html

@WebServlet("/ReadRegistration")
public class ReadRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReadRegistration() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	System.out.println("Do Get"); //do get method is getting executed automatically
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");

		out.println("<table border=1>");
		out.println("<tr>");
		out.println("<th>");
		out.println("Name");
		out.println("</th>");
		out.println("<th>");
		out.println("Email Id");
		out.println("</th>");
		out.println("<th>");
		out.println("Mobile");
		out.println("</th>");
		out.println("</tr>");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root", "root");
			
			Statement stmnt =con.createStatement();
			ResultSet result = stmnt.executeQuery("select * from user");
			
			while(result.next()) {
				out.print("<tr>");
				
				out.print("<td>");
				out.println(result.getString(1));
				out.print("</td>");
				
				out.print("<td>");
				out.println(result.getString(2));
				out.print("</td>");
				
				out.print("<td>");
				out.println(result.getString(3));
				out.print("</td>");
				
				out.print("</tr>");
				
			}
			
			con.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("</table>");
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do Post");
	
	}

}
