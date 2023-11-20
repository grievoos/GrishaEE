package com.example.grishaee.Servlets.DeleteServlets;

import com.example.grishaee.DAO.ChoiceDAO;
import com.example.grishaee.DAO.Connection.ConnectionProperty;
import com.example.grishaee.DAO.QuestionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteQuestion")
public class DeleteQuestionServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final QuestionDAO dao;

    public DeleteQuestionServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new QuestionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long deleteid = (strId != null) ? Long.parseLong(strId) : null;
        try {
            dao.delete(deleteid);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/GrishaEE_war_exploded/questions");
    }
}