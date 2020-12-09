package controller;

import entities.User;
import services.CategoryService;
import services.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(!PrivelegeController.isUser(session)){
            response.sendRedirect("index.jsp");
            return;
        }

        String filter = request.getParameter("filter");

        if(filter != null){
            session.setAttribute("items", ItemService.getItems(filter));
            session.setAttribute("products", CategoryService.getCategories());
            response.sendRedirect("products.jsp?filter=" + filter + "#content");
        }else{
            session.setAttribute("items", ItemService.getAllItems());
            session.setAttribute("products", CategoryService.getCategories());
            response.sendRedirect("products.jsp");
        }


    }
}
