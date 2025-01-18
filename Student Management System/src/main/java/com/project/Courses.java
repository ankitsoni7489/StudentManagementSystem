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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebServlet("/Courses")
public class Courses extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("Username");
        response.setContentType("text/html");
        
        List<Map<String, Object>> courses = new ArrayList<>();

      try (Connection conn = utils.DBConnection.getConnection()) {
    	  String query = "SELECT \r\n"
    	  		+ "                c.CourseID, \r\n"
    	  		+ "                c.CourseName,\r\n"
    	  		+ "                c.CourseCode, \r\n"
    	  		+ "                c.Credits,\r\n"
    	  		+ "                c.Department \r\n"
    	  		
    	  		+ "            FROM \r\n"
    	  		+ "                Users u\r\n"
    	  		+ "            INNER JOIN \r\n"
    	  		+ "                Student s ON u.StudentID = s.StudentID\r\n"
    	  		+ "            INNER JOIN \r\n"
    	  		+ "                ExamAndResult er ON s.StudentID = er.StudentID\r\n"
    	  		+ "            INNER JOIN \r\n"
    	  		+ "                Course c ON er.CourseID = c.CourseID\r\n"
    	  		+ "            WHERE \r\n"
    	  		+ "                u.Username =  ? \r\n"
    	  		+ "        ;";

          PreparedStatement stmt = conn.prepareStatement(query);
          stmt.setString(1, username);

          ResultSet rs = stmt.executeQuery();
              
              while (rs.next()) {
                  Map<String, Object> course = new HashMap<>();
                  course.put("CourseID", rs.getInt("CourseID"));
                  course.put("CourseName", rs.getString("CourseName"));
                  course.put("CourseCode", rs.getString("CourseCode"));
                  course.put("Credits", rs.getString("Credits"));
                  course.put("Department", rs.getString("Department"));
                  courses.add(course);
              }

              request.setAttribute("courses", courses);
              request.setAttribute("username",username);
              RequestDispatcher rd = request.getRequestDispatcher("course.jsp");
              rd.forward(request, response);
              PrintWriter out = response.getWriter();
        		
      } catch (Exception e) {
          e.printStackTrace();
          response.sendRedirect("login.jsp?error=Something went wrong");
      }
      
	}
}
