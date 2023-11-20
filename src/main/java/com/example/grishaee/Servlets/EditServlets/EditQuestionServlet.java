package com.example.grishaee.Servlets.EditServlets;

import com.example.grishaee.DAO.Connection.ConnectionProperty;
import com.example.grishaee.DAO.QuestionDAO;
import com.example.grishaee.DAO.VoteDAO;
import com.example.grishaee.Models.Question;
import com.example.grishaee.Models.Vote;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editQuestion")
public class EditQuestionServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final VoteDAO voteDAO;
    private final QuestionDAO questionDAO;

    public EditQuestionServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        voteDAO = new VoteDAO();
        questionDAO = new QuestionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vote> votes;
        List<Question> questions;
        String strId = request.getParameter("id");
        Long editQuestionId = (strId != null) ? Long.parseLong(strId) : null;
        Question editQuestion;
        try {
            votes = voteDAO.findAll();
            questions = questionDAO.findAll();
            for (Question ch:questions){
                ch.setVote(voteDAO.FindById(ch.getVoteId(), votes));
            }
            editQuestion = questionDAO.findById(editQuestionId);
            editQuestion.setVote(voteDAO.findById(editQuestion.getVoteId()));
            votes.removeIf(vote1 -> vote1.getId() == editQuestion.getVoteId());
            request.setAttribute("votes", votes);
            request.setAttribute("questions", questions);
            request.setAttribute("questionEdit", editQuestion);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/edit/editquestion.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String strId = request.getParameter("id");
            Long editQuestionId = (strId != null) ? Long.parseLong(strId) : null;
            String vote = request.getParameter("voteField");
            int index1 = vote.indexOf('=');
            int index2 = vote.indexOf(",");
            String r1 = vote.substring(index1+1, index2);
            long voteId = Long.parseLong(r1.trim());
            Vote votes= voteDAO.findById(voteId);
            questionDAO.update(new Question(editQuestionId, voteId, request.getParameter("content"),
                    request.getParameter("dateVote"),  votes));
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/GrishaEE_war_exploded/questions");
    }
}
