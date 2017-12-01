package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;



/**
 * Servlet implementation class MyDisplayServlet
 */
@WebServlet({ "/display", "/view", "/show*" })
public class MyDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyDisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html> <body> <h1> My First Servlet</h1>");
		out.println("name is"+request.getParameter("name"));
		out.println("preference is"+request.getParameter("pref"));
		
		//Driver
				try {
					Class.forName("com.mysql.jdbc.Driver");
				
				//connection
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyFirstTest?useSSL=false", "root", "410428");
			//Statement
				Statement statement = connection.createStatement();
			//Qurey
				ResultSet rSet = statement.executeQuery("SELECT * FROM MyFirstTest.student;");
				//Processing
				while (rSet.next()) {
					Student student = new Student();
					out.println("<ul>");
					out.println("<li>");
					student.setStudentId(rSet.getInt(1));
					
					student.setName(rSet.getString("name"));
				
					student.setNickName(rSet.getString(3));
					
					out.println(student.toString());
					out.println("</li>");
					out.println("</ul>");
					
				}
				//Releasing
				statement.close();
				connection.close();
		out.println("<br/>");
		out.append("Served at: ").append(request.getContextPath());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
