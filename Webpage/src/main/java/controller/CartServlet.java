package controller;

import entities.Item;
import entities.User;
import services.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(!PrivelegeController.isUser(session)){
            response.sendRedirect("index.jsp");
            return;
        }

        if(request.getParameter("itemID") != null){
            addToCart(request, response, session);
        }else if(request.getParameter("removeItem") != null){
            removeFromCart(request, response, session);
        }else{
            response.sendRedirect("cart.jsp");
        }

    }

    public void addToCart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException{


        int itemID = Integer.parseInt(request.getParameter("itemID"));
        Item item = ItemService.getItem(itemID);
        if (item.getQuantity() > 0) {
            User user = (User) session.getAttribute("user");
            user.getCart().add(item);
        }

        String filter = request.getParameter("filter");
        if(filter != null){
            response.sendRedirect("products.jsp?filter=" + filter + "#content");
        }else{
            response.sendRedirect("products.jsp?#content");
        }

    }

    public void removeFromCart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException{
        int itemID = Integer.parseInt(request.getParameter("removeItem"));
        User user = (User) session.getAttribute("user");
        user.getCart().removeFromCart(itemID);
        response.sendRedirect("cart.jsp?#content");
    }
}
