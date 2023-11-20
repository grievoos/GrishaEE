package com.example.grishaee.Servlets.EditServlets;

import com.example.grishaee.DAO.Connection.ConnectionProperty;
import com.example.grishaee.DAO.VoteDAO;
import com.example.grishaee.Models.Vote;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet("/editVote")
public class EditVoteServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final VoteDAO dao;

    public EditVoteServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        dao = new VoteDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        List<Vote> votes;
        try {
            votes = dao.findAll();
            request.setAttribute("votes", votes);
        } catch (Exception e) {
            System.out.println(e);
        }
        String strId = request.getParameter("id");
        Long editVoteId = (strId != null) ? Long.parseLong(strId) : null;
        Vote editVote;
        try {
            editVote = dao.findById(editVoteId);
            request.setAttribute("voteEdit", editVote);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/edit/editvote.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long editVoteId = (strId != null) ? Long.parseLong(strId) : null;
        Vote updateVote = new Vote(editVoteId, request.getParameter("title"), request.getParameter("dateStart"),
                request.getParameter("dateFinish"), request.getParameter("status"));
        try {
            dao.update(updateVote);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/GrishaEE_war_exploded/votes");
    }
}
