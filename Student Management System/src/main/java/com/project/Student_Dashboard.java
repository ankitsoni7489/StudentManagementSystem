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
import java.util.List;


public class Student_Dashboard extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session = request.getSession();
          String username = (String)session.getAttribute("Username");
          response.setContentType("text/html");
          

        try (Connection conn = utils.DBConnection.getConnection()) {
            String query = "SELECT \r\n"
            		+ " "
            		+ "    Student.*\r\n"
            		+ "FROM \r\n"
            		+ "    Users\r\n"
            		+ "INNER JOIN \r\n"
            		+ "    Student \r\n"
            		+ "ON \r\n"
            		+ "    Users.StudentID = Student.StudentID\r\n"
            		+ "WHERE \r\n"
            		+ "    Users.Username =?;"
            		+ "";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("StudentID");
                String name = rs.getString("Name");
                String collage = rs.getString("College");
                String enroll = rs.getString("EnrollmentNumber");
                int sem = rs.getInt("Semester");
                String section = rs.getString("Section");
                String rollN = rs.getString("RollNumber");
                String mob = rs.getString("MobileNumber");
                String altmob = rs.getString("AlternativeNumber");
                float attendance = rs.getFloat("Attendance");
                Date DOB = rs.getDate("DOB");
                String address = rs.getString("Address");
                String gender = rs.getString("Gender");
                String email = rs.getString("Email");
               
                request.setAttribute("id",id);
                request.setAttribute("name",name);
                request.setAttribute("collage",collage);
                request.setAttribute("enroll",enroll);
                request.setAttribute("sem",sem);
                request.setAttribute("section",section);
                request.setAttribute("rollN",rollN);
                request.setAttribute("mob",mob);
                request.setAttribute("altmob",altmob);
                request.setAttribute("attendance",attendance);
                request.setAttribute("DOB",DOB);
                request.setAttribute("address",address);
                request.setAttribute("gender",gender);
                request.setAttribute("email",email);
                RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Something went wrong");
        }
        
//        String str = "asdfgghjkl";
//        request.setAttribute("str", str);
    }
}


