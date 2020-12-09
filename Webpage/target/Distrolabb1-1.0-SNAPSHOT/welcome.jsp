<%@ page import="entities.User" %>
<%@ page import="controller.PrivelegeController" %><%--
  Created by IntelliJ IDEA.
  User: Aigeth
  Date: 2020-11-19
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Welcome</title>
    <link rel="stylesheet" href="./css/style.css" />
    <link rel="stylesheet" href="./css/welcome.css" />
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
<div class="background"> </div>
<div class="navbar">
    <a class="button" href="./welcome.jsp"><p class="text">Home</p></a>
    <a class="button" href="/ProductServlet"><p class="text">Products</p></a>
    <a class="button" href="./about.jsp"><p class="text">About us</p></a>
    <a class="button" href="./contact.jsp"><p class="text">Contact</p></a>
    <a href="/CartServlet" class="cart_icon">
        <i class="fas fa-shopping-cart fa-2x my-cartfloat"></i>
        <span class="cart_count"><%
            if(user.getCart().size()> 0){
                out.print(user.getCart().size());
            }
        %></span>
    </a>
    <a href="logout" class="logout_icon">
        <i class="fas fa-sign-out-alt fa-2x my-logoutfloat"></i>
        <h5>Sign out</h5>
    </a>
</div>
<div class="banner">
    <h1>Welcome <%=user.getFirstname()%></h1>
    <h2>Scroll down for exclusive deals</h2>
    <a href="#content" class="scroll_icon">
        <i class="fas fa-angle-double-down fa-8x bounce_animation"></i>
    </a>
</div>
<div class="content" id="content">
    <img src="./resources/deal1.jpeg" alt="deal 1" class="deals" />
    <p class="deal_text">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quas natus,
        odit nisi eligendi quae velit ad mollitia voluptates harum? Fuga quae
        nesciunt laboriosam nobis facilis voluptatem error ipsam dignissimos
        cumque consequatur molestiae consectetur ut, veritatis sit id animi
        adipisci. Incidunt deserunt expedita consequatur iusto quia similique
        nam recusandae, dolor error blanditiis debitis rem est perspiciatis quae
        magnam illum dolores inventore voluptatibus. Possimus in enim atque.
        Praesentium, incidunt dignissimos dolore quasi provident est eos odio
        soluta iste corrupti voluptates officiis! Quas incidunt sit et magnam
        quasi iusto ullam eos quia, consequatur odit debitis, molestiae ducimus!
        Est reprehenderit possimus quae odio corporis.
    </p>
</div>
</body>
</html>
