<%@ page import="controller.PrivelegeController" %>
<%@ page import="entities.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Aigeth
  Date: 2020-12-05
  Time: 02:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cart</title>
    <link rel="stylesheet" href="./css/style.css" />
    <link rel="stylesheet" href="./css/items.css" />
    <script
            src="https://kit.fontawesome.com/89f509dd05.js"
            crossorigin="anonymous"
    ></script>
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
            href="https://fonts.googleapis.com/css2?family=Teko&display=swap"
            rel="stylesheet"
    />
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if(!PrivelegeController.isUser(session)){
        response.sendRedirect("index.jsp");
        return;
    }
    User user = (User) session.getAttribute("user");
%>
<div class="navbar">
    <a class="button" href="./welcome.jsp"><p class="text">Home</p></a>
    <a class="button" href="./products.jsp"><p class="text">Products</p></a>
    <a class="button" href="./about.jsp"><p class="text">About us</p></a>
    <a class="button" href="./contact.jsp"><p class="text">Contact</p></a>
    <a href="logout" class="logout_icon">
        <i class="fas fa-sign-out-alt fa-2x my-logoutfloat"></i>
        <h5>Sign out</h5>
    </a>
</div>
<div class="banner">
    <%
        String string = (String) session.getAttribute("paymentStatus");
        if(string.equals("Payment Successful!")){
            out.println("<h1>" + string + "</h1>");
            out.println("<h2>But are you sure you did not miss something? " +
                    "Make sure by taking a peek at the store again. </h2>");
        }else{
            out.println("<h2>" + string + "</h2>");
        }
    %>

</div>

</body>
</html>
