package ems;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet(urlPatterns="/create")
public class CreateServlet extends HttpServlet {
	@Override
	public void service(ServletRequest req,ServletResponse res) {
		int id=Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String loc = req.getParameter("loc");
		String date = req.getParameter("date");
		String guest = req.getParameter("guest");
		
//		System.out.println(id);
//		System.out.println(title);
//		System.out.println(loc);
//		System.out.println(date);
//		System.out.println(guest);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "root");
			PreparedStatement ps = con.prepareStatement("insert into event values (?,?,?,?,?)");		
			ps.setInt(1, id);
			ps.setString(2,title);
			ps.setString(3, loc);
			ps.setString(4, date);
			ps.setString(5, guest);		
			int row = ps.executeUpdate();
			System.out.println(row + ": event details is added");			
			PrintWriter pw =  res.getWriter();
			pw.write("<h1>"+row+ "Account Created</h1>");	
			RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
			rd.include(req, res);
			
			
			
			ps.close();
			con.close();
			
			
			
		} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
		
		
	}

}
