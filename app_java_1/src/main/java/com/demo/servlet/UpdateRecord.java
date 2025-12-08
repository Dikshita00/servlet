package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateReg")
public class UpdateRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UpdateRecord() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out =response.getWriter();
        out.print("hello");
        out.print("<input type='text/'>");
        
        out.print("<table>");  //this will sent it to html page where a table get created
        out.print("<tr>"); //table row
        out.print("<th>"); //table header
        out.print("Name");
        out.print("</th>");
        out.print("<th>");
        out.print("Email Id");
        out.print("</th>");
        out.print("</tr>");
        
        out.print("</table>");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailId");
		String mobile = request.getParameter("mobile");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration","root","root");
			
			Statement stmnt = con.createStatement();
			stmnt.executeUpdate("update student set mobile='"+mobile+              "' where email='"+email+"'");
		
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

}