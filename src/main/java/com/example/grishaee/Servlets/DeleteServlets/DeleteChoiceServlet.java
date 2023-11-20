package com.example.grishaee.Servlets.DeleteServlets;

import com.example.grishaee.DAO.ChoiceDAO;
import com.example.grishaee.DAO.Connection.ConnectionProperty;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteChoice")
public class DeleteChoiceServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final ChoiceDAO dao;

    public DeleteChoiceServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new ChoiceDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long deleteid = (strId != null) ? Long.parseLong(strId) : null;
        try {
            dao.delete(deleteid);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/GrishaEE_war_exploded/choice");
    }
}
