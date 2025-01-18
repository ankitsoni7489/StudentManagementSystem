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

//@WebServlet("/Courses")
public class Fees extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("Username");
        response.setContentType("text/html");
        
        List<Map<String, Object>> fees = new ArrayList<>();

      try (Connection conn = utils.DBConnection.getConnection()) {
    	  String query = "SELECT \r\n"
    	  		+ "    u.Username,\r\n"
    	  		+ "    s.Name AS StudentName,\r\n"
    	  		+ "    f.TotalFee,\r\n"
    	  		+ "    f.PaidAmount,\r\n"
    	  		+ "    f.DueAmount,\r\n"
    	  		+ "    f.LastPaymentDate,\r\n"
    	  		+ "    f.PaymentStatus\r\n"
    	  		+ "FROM \r\n"
    	  		+ "    Users u\r\n"
    	  		+ "INNER JOIN Student s ON u.StudentID = s.StudentID\r\n"
    	  		+ "INNER JOIN Fees f ON s.StudentID = f.StudentID\r\n"
    	  		+ "WHERE \r\n"
    	  		+ "    u.Username = ? ;";

          PreparedStatement stmt = conn.prepareStatement(query);
          stmt.setString(1, username);

          ResultSet rs = stmt.executeQuery();
              
              while (rs.next()) {
                  Map<String, Object> fee = new HashMap<>();
                  fee.put("TotalFee", rs.getFloat("TotalFee"));
                  fee.put("PaidAmount", rs.getFloat("PaidAmount"));
                  fee.put("DueAmount", rs.getFloat("DueAmount"));
                  fee.put("LastPaymentDate", rs.getString("LastPaymentDate"));
                  fee.put("PaymentStatus", rs.getString("PaymentStatus"));
                  fees.add(fee);                  
              }

              request.setAttribute("fees", fees);
              request.setAttribute("username",username);
              RequestDispatcher rd = request.getRequestDispatcher("fees.jsp");
              rd.forward(request, response);
//              	PrintWriter out = response.getWriter();
//              	for (Map<String, Object> fruit : fees) {
//              		 out.println(fruit);
//              		}
//        		
      } catch (Exception e) {
          e.printStackTrace();
          response.sendRedirect("login.jsp?error=Something went wrong");
      }
      
	}
}
