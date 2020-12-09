<%@ page import="controller.PrivelegeController" %>
<%@ page import="entities.User" %>
<%@ page import="entities.Item" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Aigeth
  Date: 2020-12-07
  Time: 12:03
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
    <h1>Cart</h1>
    <h2>We offer multiple payment options</h2>
    <a href="#content" class="scroll_icon">
        <i class="fas fa-angle-double-down fa-8x bounce_animation"></i>
    </a>
</div>
<div class="content filter" id="content" style="padding-top:5%">
    <table>
        <tr>
            <th>Item</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Remove</th>
        </tr>
        <%
            ArrayList<Item> items = user.getCart().getItems();
            for(Item item : items){
                out.println("<tr>");
                out.println("<td>" + item.getName() + "</td>");
                out.println("<td>" + item.getPrice() + "</td>");
                out.println("<td>" + item.getQuantity() + "</td>");
                out.println("<td>");
                out.println("<h3>");
                out.println("<a href=\"/CartServlet?removeItem=" + item.getId() + "\" class=\"my-addtocartfloat\">");
                out.println("<i class=\"far fa-trash-alt fa-2x\"></i></a>");
                out.println("</h3>");
                out.println("</td>");
                out.println("</tr>");
            }
        %>
    </table>
    <table>
        <tr>
            <th>Total price:&nbsp; <%=user.getCart().getSum()%>&nbsp; SEK</th>
            <th>
                Pay with: <% out.print(" "); out.print(" ");%> <a href="/PaymentServlet" class="my-addtocartfloat">
                <i class="fas fa-credit-card fa-2x" style="margin-left: 16px"></i></a>
            </th>
        </tr>
    </table>
</div>
</body>
</html>
