package com.project;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/attendance")
public class Attendance extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("Username");
        response.setContentType("text/html");
        
        List<Map<String, Object>> attendance = new ArrayList<>();

      try (Connection conn = utils.DBConnection.getConnection()) {
    	  String query = "SELECT \r\n"
    	  		
    	  		
    	  		+ "    c.CourseName,\r\n"
    	  		+ "    a.AttendanceDate,\r\n"
    	  		+ "    a.Status\r\n"
    	  		+ "FROM \r\n"
    	  		+ "    Users u\r\n"
    	  		+ "INNER JOIN Student s ON u.StudentID = s.StudentID\r\n"
    	  		+ "INNER JOIN Attendance a ON s.StudentID = a.StudentID\r\n"
    	  		+ "INNER JOIN Course c ON a.CourseID = c.CourseID\r\n"
    	  		+ "WHERE \r\n"
    	  		+ "    u.Username = ? ;";

          PreparedStatement stmt = conn.prepareStatement(query);
          stmt.setString(1, username);

          ResultSet rs = stmt.executeQuery();
              
              while (rs.next()) {
                  Map<String, Object> atten = new HashMap<>();
                  atten.put("CourseName", rs.getString("CourseName"));
                  atten.put("AttendanceDate", rs.getString("AttendanceDate"));
                  atten.put("Status", rs.getString("Status"));
                 
                  attendance.add(atten);                  
              }

              request.setAttribute("attendance", attendance);
              request.setAttribute("username",username);
              RequestDispatcher rd = request.getRequestDispatcher("attendance.jsp");
              rd.forward(request, response);
//        	PrintWriter out = response.getWriter();
//        	for (Map<String, Object> fruit : attendance) {
//        		 out.println(fruit);
//        		}

       		
      } catch (Exception e) {
          e.printStackTrace();
          response.sendRedirect("login.jsp?error=Something went wrong");
      }
      
	}
}
