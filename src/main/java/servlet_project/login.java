package servlet_project;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginuser")
public class login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String myemail = req.getParameter("username");
		String mypass = req.getParameter("password");

		PrintWriter out = resp.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch215?useSSL=false", "root",
					"root");
			PreparedStatement ps = c.prepareStatement("select * from register where email=? AND password=?");
			ps.setString(1, myemail);
			ps.setString(2, mypass);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				req.setAttribute("FirstName", rs.getString(1));
				req.setAttribute("LastName", rs.getString(2));
				req.setAttribute("email", rs.getString(3));
				req.setAttribute("gender", rs.getString(4));
				req.setAttribute("city", rs.getString(5));
				req.setAttribute("state", rs.getString(6));
				req.setAttribute("country", rs.getString(7));
				req.setAttribute("education", rs.getString(8));
				req.setAttribute("10pass", rs.getString(7));
				req.setAttribute("12pass", rs.getString(8));
				req.setAttribute("dob", rs.getString(9));
				req.setAttribute("skill", rs.getString(10));
				
				out.print("<h1>" + "Login Succcesfully...." + "</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
				rd.include(req, resp);

			} else {
				System.out.println("Invalid Credentials....");
				out.print("Invalid Credentials........");
				RequestDispatcher rd = req.getRequestDispatcher("/Home.html");
				rd.include(req, resp);
			}

		} catch (Exception e) {
		}

	}

}
