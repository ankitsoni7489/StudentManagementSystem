package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBConnection;

public class LoginServlet extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");

        try (Connection conn = utils.DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE Username = ? AND Password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            jakarta.servlet.http.HttpSession session = request.getSession();
            session.setAttribute("Username", username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    RequestDispatcher rd = request.getRequestDispatcher("Student_Dashboard");
            		rd.forward(request,response);
                    
                } else {
                  
                    response.sendRedirect("login.jsp?error=Invalid credentials");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Something went wrong");
        }
        
    }
    
}
