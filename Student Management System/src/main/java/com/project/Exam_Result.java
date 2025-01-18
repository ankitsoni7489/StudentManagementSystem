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

@WebServlet("/examandresult")
public class Exam_Result extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("Username");
        response.setContentType("text/html");
        
        List<Map<String, Object>> examResult = new ArrayList<>();

      try (Connection conn = utils.DBConnection.getConnection()) {
    	  String query = "SELECT \r\n"
    	  		
    	  		+ "    c.CourseName,\r\n"
    	  		+ "    er.ExamName,\r\n"
    	  		+ "    er.ExamDate,\r\n"
    	  		+ "    er.MarksObtained,\r\n"
    	  		+ "    er.TotalMarks,\r\n"
    	  		+ "    er.Grade\r\n"
    	  		+ "FROM \r\n"
    	  		+ "    Users u\r\n"
    	  		+ "INNER JOIN Student s ON u.StudentID = s.StudentID\r\n"
    	  		+ "INNER JOIN ExamAndResult er ON s.StudentID = er.StudentID\r\n"
    	  		+ "INNER JOIN Course c ON er.CourseID = c.CourseID\r\n"
    	  		+ "WHERE \r\n"
    	  		+ "    u.Username = ? ;";

          PreparedStatement stmt = conn.prepareStatement(query);
          stmt.setString(1, username);

          ResultSet rs = stmt.executeQuery();
              
              while (rs.next()) {
                  Map<String, Object> exam = new HashMap<>();
                  exam.put("CourseName", rs.getString("CourseName"));
                  exam.put("ExamName", rs.getString("ExamName"));
                  exam.put("ExamDate", rs.getString("ExamDate"));
                  exam.put("MarksObtained", rs.getFloat("MarksObtained"));
                  exam.put("TotalMarks", rs.getFloat("TotalMarks"));
                  exam.put("Grade", rs.getString("Grade"));
                  examResult.add(exam);                  
              }

              request.setAttribute("examResult", examResult);
              request.setAttribute("username",username);
              RequestDispatcher rd = request.getRequestDispatcher("examandresult.jsp");
              rd.forward(request, response);
//        	PrintWriter out = response.getWriter();
//        	for (Map<String, Object> fruit : examResult) {
//        		 out.println(fruit);
//        		}

       		
      } catch (Exception e) {
          e.printStackTrace();
          response.sendRedirect("login.jsp?error=Something went wrong");
      }
      
	}
}
