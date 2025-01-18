<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fees</title>
<link rel="stylesheet" href="css\examandresult.css">
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
            <li id="dashboard" class="itme1"><a href="Student_Dashboard" target="_blank">Dashboard</a></li>
            <li id="course" class="itme1"><a href="Courses" target="_blank">Course</a></li>
            <li id="exam" class="itme1"><a href="#" target="_blank">Exam and Result</a></li>
            <li id="attendance" class="itme1"><a href="Attendance" target="_blank">Attendance</a></li>
            <li id="timetable" class="itme1"><a href="" target="_blank">Timetable</a></li>
            <li id="other" class="itme1"><a href="Fees" >Fees</a></li>
        </ul>
    </nav>
    <div class="container">
        <div class="head_name">
        	<h1>Exam and Result </h1>
        	
        </div>
        <ul class="list1">
         <% 
           
            Object coursesObj = request.getAttribute("examResult");

					if (coursesObj instanceof List) {
					    
					    @SuppressWarnings("unchecked")
					    List<Map<String, Object>> examResult = (List<Map<String, Object>>) coursesObj;
					    
					    for (Map<String, Object> exam : examResult) {
    	%>
             
            
			
            <li id="sem" class="dash">
            	Course Name: <%= exam.get("CourseName") %><br>
            	Exam Name: <%= exam.get("ExamName") %><br>
            	Exam Date: <%= exam.get("ExamDate") %><br>
            	Mark Obtained: <%= exam.get("MarksObtained") %><br>
            	Total Mark: <%= exam.get("TotalMarks") %><br>
            	Status: <%= exam.get("Grade") %>
            </li>
        <% 
                }
            } else {
        %>
            		<li id="dep" class="dash">Sorry there try again later</li>
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