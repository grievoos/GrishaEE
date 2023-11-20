package com.example.grishaee.Servlets.EditServlets;

import com.example.grishaee.DAO.Connection.ConnectionProperty;
import com.example.grishaee.DAO.QuestionDAO;
import com.example.grishaee.DAO.UserDAO;
import com.example.grishaee.DAO.VoteDAO;
import com.example.grishaee.Models.Question;
import com.example.grishaee.Models.User;
import com.example.grishaee.Models.Vote;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final UserDAO dao;

    public EditUserServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        dao = new UserDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List<User> users;
        try {
            users = dao.findAll();
            request.setAttribute("users", users);
        } catch (Exception e) {
            System.out.println(e);
        }
        String strId = request.getParameter("id");
        Long editUserId = (strId != null) ? Long.parseLong(strId) : null;
        User editUser;
        try {
            editUser = dao.findById(editUserId);
            request.setAttribute("userEdit", editUser);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/edit/edituser.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long editUserId = (strId != null) ? Long.parseLong(strId) : null;
        User updateUser = new User(editUserId, request.getParameter("secondName"), request.getParameter("firstName"),
                request.getParameter("lastName"), request.getParameter("email"),
                request.getParameter("phone"), request.getParameter("statusUser"));
        try {
            dao.update(updateUser);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/GrishaEE_war_exploded/users");
    }
}