package ems;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet("/delete")
public class DeleteEventsServlet extends HttpServlet{
		@Override
	public void service(ServletRequest req,ServletResponse res) throws ServletException, IOException{
		int id=Integer.parseInt(req.getParameter("id"));
		System.out.println(id);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "root");
			PreparedStatement ps=con.prepareStatement("delete from event where id=?");
			ps.setInt(1, id);
			int row=ps.executeUpdate();
			System.out.println(row+" : is Deleted");
			
			PrintWriter pw=res.getWriter();
			pw.write("<h1>Employee Deleted Successfully`</h1>");
			
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
