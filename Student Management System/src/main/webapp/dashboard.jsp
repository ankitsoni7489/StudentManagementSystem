<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="css\dashboard.css">
</head>
<body>

	
	<%  Object id = request.getAttribute("id");
		String name = request.getAttribute("name").toString();
		String collage = request.getAttribute("collage").toString();
		String enroll = request.getAttribute("enroll").toString();
		Object sem = request.getAttribute("sem");
		String section = request.getAttribute("section").toString();
		String rollN = request.getAttribute("rollN").toString();
		String mob = request.getAttribute("mob").toString();
		String altmob = request.getAttribute("altmob").toString();
		Object attendance = request.getAttribute("attendance");
		Object DOB = request.getAttribute("DOB");
		String gender = request.getAttribute("gender").toString();
		String email = request.getAttribute("email").toString();
		String address = request.getAttribute("address").toString();
		
		 
	%>
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
            <li id="dashboard" class="itme1"><a href="#" >Dashboard</a></li>
            <li id="course" class="itme1"><a href="Courses" target="_blank">Course</a></li>
            <li id="exam" class="itme1"><a href="Exam_Result">Exam and Result</a></li>
            <li id="attendance" class="itme1"><a href="Attendance">Attendance</a></li>
            <li id="timetable" class="itme1"><a href="">Timetable</a></li>
            <li id="other" class="itme1"><a href="Fees" target="_blank">Fees</a></li>
        </ul>
    </nav>
    
	    <div class="container">
	    
	        <div class="head_name">
	        	<h1>Hello  </h1>
	        	<p class="name"><% out.println(name);%></p>
	        </div>
	        <ul class="list1">
	            <li class="dash">Enrollment ID: -  <% out.println(id); %> </li>
	            <li id="sem" class="dash">Semester: -  <% out.println(sem); %></li>
	            <li id="collage" class="dash">Collage: -  <% out.println(collage); %></li>
	            <li id="dep" class="dash">Department: - CSE</li>
	            <li id="roll" class="dash">Roll Number: -  <% out.println(rollN); %></li>
	            <li id="gender" class="dash">Gender: -  <% out.println(gender); %></li>
	            <li id="dob" class="dash">Date of Birth: -  <% out.println(DOB); %></li>
	            <li id="mob" class="dash">Mobile: -  <% out.println(mob); %></li>
	            <li id="mob" class="dash">Altenative Mobile: -  <% out.println(altmob); %></li>
	            <li id="email" class="dash">Email: -  <% out.println(email); %></li>
	            <li id="other" class="dash">Address: -  <% out.println(address); %></li>
	            <li id="section" class="dash">Section: -  <% out.println(section); %></li>
	            <li id="fees" class="dash">Fees: - </li>
	            <li id="attendance" class="dash">Attendance: -  <% out.println(attendance); %></li>
	            
	        </ul>
	     
	    </div>
	
	</div>
    <footer class="footer">
        <p>Contact Us 1234567890</p>
    </footer>
</body>
</html>