package com.example.grishaee.Servlets.EditServlets;

import com.example.grishaee.DAO.ChoiceDAO;
import com.example.grishaee.DAO.Connection.ConnectionProperty;
import com.example.grishaee.DAO.QuestionDAO;
import com.example.grishaee.DAO.UserDAO;
import com.example.grishaee.Models.Choices;
import com.example.grishaee.Models.Question;
import com.example.grishaee.Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editChoice")
public class EditChoiceServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final QuestionDAO questionDAO;
    private final UserDAO userDAO;
    private final ChoiceDAO choiceDAO;

    public EditChoiceServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        questionDAO = new QuestionDAO();
        userDAO = new UserDAO();
        choiceDAO = new ChoiceDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> questions;
        List<User> users;
        List<Choices> choices;

        String strId = request.getParameter("id");
        Long editChoiceId = (strId != null) ? Long.parseLong(strId) : null;
        Choices editChoice;
        try {
            users = userDAO.findAll();
            questions = questionDAO.findAll();
            choices = choiceDAO.findAll();
            for (Choices ch:choices){
                ch.setQuestion(questionDAO.FindById(ch.getQuestionId(), questions));
                ch.setUser(userDAO.FindById(ch.getUserId(), users));
            }

            editChoice = choiceDAO.findById(editChoiceId);
            editChoice.setQuestion(questionDAO.findById(editChoice.getQuestionId()));
            questions.removeIf(question1 -> question1.getId() == editChoice.getQuestionId());
            editChoice.setUser(userDAO.findById(editChoice.getUserId()));
            users.removeIf(user1 -> user1.getId() == editChoice.getUserId());
            request.setAttribute("questions", questions);
            request.setAttribute("users", users);
            request.setAttribute("choices", choices);
            request.setAttribute("choiceEdit", editChoice);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/edit/editchoice.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String strId = request.getParameter("id");
            Long editChoiceId = (strId != null) ? Long.parseLong(strId) : null;

            String quest = request.getParameter("questionField");
            int index1 = quest.indexOf('=');
            int index2 = quest.indexOf(",");
            String r1 = quest.substring(index1+1, index2);
            long questionId = Long.parseLong(r1.trim());
            Question questions = questionDAO.findById(questionId);

            String user = request.getParameter("userField");
            int index3 = user.indexOf('=');
            int index4 = user.indexOf(",");
            String r2 = user.substring(index3+1, index4);
            long userId = Long.parseLong(r2.trim());
            User users = userDAO.findById(userId);

            choiceDAO.update(new Choices(editChoiceId, questionId, userId, request.getParameter("userStatus"), questions, users));
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/GrishaEE_war_exploded/choice");
    }
}
