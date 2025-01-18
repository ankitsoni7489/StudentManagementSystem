<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/log.css">
<title>Login</title>
</head>
<body>
    <div class="inner">
        <div class="container">
            <h1 class="login">Login</h1>
            <form action="login" method="Get">
                <div class="input">
                    <input type="text" name="username" placeholder="Username" required> 
                </div>
                <div class="input">
                    <input type="password" name="password" placeholder="Password" required> 
                </div>
                <div class="button">
                    <input type="submit" name="submit" id="submit" value="Login"> 
                </div>
            </form>
            
        </div>
    </div>
</body>
</html>
