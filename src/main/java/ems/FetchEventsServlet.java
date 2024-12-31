package ems;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns="/viewall")
public class FetchEventsServlet extends HttpServlet {
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root" , "root");
			PreparedStatement ps = con.prepareStatement("select * from event");
			ResultSet rs = ps.executeQuery();
			req.setAttribute("rs", rs);
//			PrintWriter pw =  res.getWriter();
//			pw.write("<html><body><table border>");
//			pw.write("<tr><th>Id</th>");
//			pw.write("<th>Name</th>");
//			pw.write("<th>Email</th>");
//			pw.write("<th>Phone</th>");
//			pw.write("<th>Password</th></tr>");
//			while(rs.next())
//			{
//				pw.write("<tr><td>"+rs.getInt(1)+"</td>");
//				pw.write("<td>"+rs.getString(2)+"</td>");
//				pw.write("<td>"+rs.getString(3)+"</td>");
//				pw.write("<td>"+rs.getString(4)+"</td>");
//				pw.write("<td>"+rs.getString(5)+"</td></tr>");
//				//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
//			}
			RequestDispatcher rd=req.getRequestDispatcher("viewall.jsp");
			rd.forward(req, res);
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		}
	}
}