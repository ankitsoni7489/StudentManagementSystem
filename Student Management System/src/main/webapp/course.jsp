<%-- <%@ taglib uri="http://jakarta.ee/jsp/jstl/core" prefix="c" %> --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Courses</title>

 <link rel="stylesheet" href="css\course.css">

</head>
<body>
    <header class="header">
        <div class="header_div">
        	<h1 id="logo">School Management System</h1>
        	<a href="#"><img src="image\image.jpeg" alt="Image"  id="header_photo"></a>
         </div>
       
    </header>
	<div class="box">
		<nav class="navbar">
        <img src="image\image.jpeg" alt="Image" class="itme1" id="passport">
        <ul class="list">
            <li id="dashboard" class="itme1"><a href="Student_Dashboard" >Dashboard</a></li>
            <li id="course" class="itme1"><a href="Courses" target="_blank">Course</a></li>
            <li id="exam" class="itme1"><a href="Exam_Result">Exam and Result</a></li>
            <li id="attendance" class="itme1"><a href="Attendance">Attendance</a></li>
            <li id="timetable" class="itme1"><a href="">Timetable</a></li>
            <li id="other" class="itme1"><a href="Fees" target="_blank">Fees</a></li>
        </ul>
    </nav>
    
    <div class="container">
        <div class="head_name">
        	<h1>Courses </h1>
        	
        </div>
        <ul class="list1">
            
            
        <% 
           
            Object coursesObj = request.getAttribute("courses");

					if (coursesObj instanceof List) {
					    
					    @SuppressWarnings("unchecked")
					    List<Map<String, Object>> courses = (List<Map<String, Object>>) coursesObj;
					    
					    for (Map<String, Object> course : courses) {
                          
                        String courseId = Integer.toString((Integer) course.get("CourseID"));
                        String courseName = (String) course.get("CourseName");
                        String courseCode = (String) course.get("CourseCode");
                        String credit = (String) course.get("Credits");
                        String dep = (String) course.get("Department");				 
    	%>
             
            
			            <li id="dep" class="dash">
			            Course : -  <%= courseName %><br>
			            Course Code: - <%= courseCode %><br>
			            Course Credit: - <%= credit %><br>
			            Department: - <%= dep %>
			            </li>
            

        
        <% 
                }
            } else {
        %>
            		<li id="dep" class="dash">No Courses Available</li>
        <% 
            }
        %>
        </ul>
    </div>
	</div>
    <footer class="footer">
        <p>Contact Us 1234567890</p>
    </footer>
</body>
</html>