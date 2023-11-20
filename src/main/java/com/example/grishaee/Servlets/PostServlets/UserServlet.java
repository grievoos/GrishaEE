package com.example.grishaee.Servlets.PostServlets;

import java.io.*;
import java.util.List;

import com.example.grishaee.DAO.Connection.ConnectionProperty;
import com.example.grishaee.DAO.UserDAO;
import com.example.grishaee.Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final UserDAO dao;

    public UserServlet() throws FileNotFoundException, IOException{
        super();
        prop = new ConnectionProperty();
        dao = new UserDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users;
        try{
            users = dao.findAll();
            request.setAttribute("users", users);
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/users.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            dao.insert(new User(request.getParameter("secondName"), request.getParameter("firstName"), request.getParameter("lastName"),
                    request.getParameter("email"), request.getParameter("phone"), request.getParameter("statusUser")));
        }catch (Exception e){
            System.out.println(e);
        }
        doGet(request, response);
    }
}