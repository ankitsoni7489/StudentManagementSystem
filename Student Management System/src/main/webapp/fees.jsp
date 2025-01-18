<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fees</title>
<link rel="stylesheet" href="css\fees.css">
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
            <li id="exam" class="itme1"><a href="Exam_Result" target="_blank">Exam and Result</a></li>
            <li id="attendance" class="itme1"><a href="Attendance" target="_blank">Attendance</a></li>
            <li id="timetable" class="itme1"><a href="" target="_blank">Timetable</a></li>
            <li id="other" class="itme1"><a href="#" >Fees</a></li>
        </ul>
    </nav>
    <div class="container">
        <div class="head_name">
        	<h1>Fees Section </h1>
        	
        </div>
        <ul class="list1">
         <% 
           
            Object coursesObj = request.getAttribute("fees");

					if (coursesObj instanceof List) {
					    
					    @SuppressWarnings("unchecked")
					    List<Map<String, Object>> fees = (List<Map<String, Object>>) coursesObj;
					    
					    for (Map<String, Object> fee : fees) {
    	%>
             
            
			<li id="sem" class="dash">Total Fees: <%= fee.get("TotalFee") %></li>
            <li id="collage" class="dash">Pending Fees: <%= fee.get("DueAmount") %></li>
            <li id="dep" class="dash">Payed Fees: <%= fee.get("PaidAmount") %></li>
            <li id="roll" class="dash">Last Date of Payment: <%= fee.get("LastPaymentDate") %></li>
            <li id="roll" class="dash">Status: <%= fee.get("PaymentStatus") %></li>
               
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