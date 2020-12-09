<%@ page import="entities.User" %><%--
  Created by IntelliJ IDEA.
  User: Aigeth
  Date: 2020-11-19
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Please login!</title>
    <link rel="stylesheet" href="./css/login.css" />
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if(user != null){
        response.sendRedirect("welcome.jsp");
        return;
    }
    String status = (String) session.getAttribute("loginstatus");
    if(status != null){
        out.println(status);
    }
%>
<div class="background"></div>
<div class="loginform">
    <form action="login" method="post" class="form">
        <input
                type="text"
                name="username"
                placeholder="Username"
                class="input"
        /><br />
        <input
                type="password"
                name="password"
                placeholder="Password"
                class="input"
        /><br />
        <input type="submit" value="login" class="button" />
    </form>
</div>
</body>
</html>
