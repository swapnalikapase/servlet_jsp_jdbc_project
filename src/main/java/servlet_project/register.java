package servlet_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.DriverManager;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submit")
public class register extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name1 = req.getParameter("fname");
		String lname1 = req.getParameter("lname");
		String email1 = req.getParameter("email");
		String gender1 = req.getParameter("gender");
		String city1 = req.getParameter("city");
		String state1 = req.getParameter("state");
		String country1 = req.getParameter("country");
		String edu1 = req.getParameter("education");
		String pass1 = req.getParameter("pass10");
		String pass2 = req.getParameter("pass12");
		String dob1 = req.getParameter("dob");
		String skill = req.getParameter("skills");
		String pass12 = req.getParameter("pass");
		
		int password = Integer.parseInt(pass12);

		System.out.println(name1);
		System.out.println(lname1);
		System.out.println(email1);
		System.out.println(gender1);
		System.out.println(city1);
		System.out.println(state1);
		System.out.println(country1);
		System.out.println(edu1);
		System.out.println(pass1);
		System.out.println(pass2);
		System.out.println(dob1);
		System.out.println(skill);
		System.out.println(pass12);

		RequestDispatcher rd = req.getRequestDispatcher("/Home.html");
		rd.include(req, resp);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch215?useSSL=false", "root",
					"root");
			PreparedStatement ps = c.prepareStatement(
					"insert into register(fname,lname,email,gender,city,state,country,education,pass10,pass12,dob,skills,password)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, name1);
			ps.setString(2, lname1);
			ps.setString(3, email1);
			ps.setString(4, gender1);
			ps.setString(5, city1);
			ps.setString(6, state1);
			ps.setString(7, country1);
			ps.setString(8, edu1);
			ps.setString(9, pass1);
			ps.setString(10, pass2);
			ps.setString(11, dob1);
			ps.setString(12, skill);
			ps.setInt(13, password);
			ps.executeUpdate();
			System.out.println("Data is inserted.....");
			

		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
