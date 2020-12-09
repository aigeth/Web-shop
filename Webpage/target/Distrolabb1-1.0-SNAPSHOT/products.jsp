<%@ page import="entities.User" %>
<%@ page import="controller.PrivelegeController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Item" %>
<%@ page import="entities.Category" %><%--
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
    <title>Document</title>
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
    <a href="/logout" class="logout_icon">
        <i class="fas fa-sign-out-alt fa-2x my-logoutfloat"></i>
        <h5>Sign out</h5>
    </a>
</div>
<div class="banner">
    <h1>Products</h1>
    <h2>Here you will find the cheapest products on the market</h2>
    <a href="#content" class="scroll_icon">
        <i class="fas fa-angle-double-down fa-8x bounce_animation"></i>
    </a>
</div>
<div class="content filter" id="content">
    <nav>
        <input type="checkbox" id="check" />
        <label for="check" class="checkbtn">
            <i class="fas fa-bars">
            <%
                if(request.getParameter("filter") != null){
                    out.println("<span class=\"filtertext\">" + request.getParameter("filter") + "</span>");
                }else{
                    out.println("<span class=\"filtertext\">All products</span>");
                }
            %>
            </i
            ></label
        >
        <ul>
            <%
                ArrayList<Category> categories = (ArrayList<Category>) session.getAttribute("products");
                for(Category category : categories){
                    out.println("<li><a href=\"/ProductServlet?filter=" + category.getName() +"\">" + category.getName() +"</a></li>");
                }
            %>
        </ul>
    </nav>

    <table>
        <tr>
            <th>Item</th>
            <th>Price</th>
            <th>In stock</th>
            <th>Add to cart</th>
        </tr>
        <%
            ArrayList<Item> items = (ArrayList<Item>) session.getAttribute("items");
            for(Item item : items){
                out.println("<tr>");
                out.println("<td>" + item.getName() + "</td>");
                out.println("<td>" + item.getPrice() + "</td>");
                out.println("<td>" + item.getQuantity() + "</td>");
                out.println("<td>");
                out.println("<h3>");
                if(request.getParameter("filter") != null){
                    out.println("<a href=\"/CartServlet?itemID=" + item.getId() + "&filter=" + request.getParameter("filter")  + "\" class=\"my-addtocartfloat\">");
                }else{
                    out.println("<a href=\"/CartServlet?itemID=" + item.getId() + "\" class=\"my-addtocartfloat\">");
                }

                out.println("<i class=\"fas fa-cart-plus fa-2x\"></i></a>");
                out.println("</h3>");
                out.println("</td>");
                out.println("</tr>");
            }
        %>
    </table>
</div>
</body>
</html>

