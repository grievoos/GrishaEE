package com.example.grishaee.Servlets.PostServlets;

import java.io.*;
import java.util.List;

import com.example.grishaee.DAO.Connection.ConnectionProperty;
import com.example.grishaee.DAO.QuestionDAO;
import com.example.grishaee.DAO.VoteDAO;
import com.example.grishaee.Models.Question;
import com.example.grishaee.Models.Vote;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/questions")
public class QuestionServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final VoteDAO voteDAO;
    private final QuestionDAO questionDAO;
    public QuestionServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        questionDAO = new QuestionDAO();
        voteDAO = new VoteDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vote> votes;
        List<Question> questions;
        try{
            votes = voteDAO.findAll();
            request.setAttribute("votes", votes);
            questions = questionDAO.findAll();
            request.setAttribute("questions", questions);
            for (Question ch:questions){
                ch.setVote(voteDAO.FindById(ch.getVoteId(), votes));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/questions.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String vote = request.getParameter("votes");
            int index1 = vote.indexOf('=');
            int index2 = vote.indexOf(",");
            String r1 = vote.substring(index1+1, index2);
            long voteId = Long.parseLong(r1.trim());
            System.out.println(voteId);
            Vote votes = voteDAO.findById(voteId);
            System.out.println(voteId);
            questionDAO.insert(new Question(voteId, request.getParameter("content"), request.getParameter("dateVote"), votes));

        }catch (Exception e){
            System.out.println(e);
        }

        doGet(request, response);
    }
}