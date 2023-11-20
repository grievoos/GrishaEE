package com.example.grishaee.Servlets.PostServlets;

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

@WebServlet("/choice")
public class ChoiceServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final QuestionDAO questionDAO;
    private final UserDAO userDAO;
    private final ChoiceDAO choiceDAO;
    public ChoiceServlet() throws IOException{
        super();
        prop = new ConnectionProperty();
        questionDAO = new QuestionDAO();
        userDAO = new UserDAO();
        choiceDAO = new ChoiceDAO();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> questions;
        List<User> users;
        List<Choices> choices;
        try{
            questions = questionDAO.findAll();
            request.setAttribute("questions", questions);
            users = userDAO.findAll();
            request.setAttribute("users", users);
            choices = choiceDAO.findAll();
            request.setAttribute("choices", choices);
            for (Choices ch:choices){
                ch.setQuestion(questionDAO.FindById(ch.getQuestionId(), questions));
                ch.setUser(userDAO.FindById(ch.getUserId(), users));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/choices.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String question = request.getParameter("questions");
            int i1 = question.indexOf("=");
            int i2 = question.indexOf(",");
            String r1 = question.substring(i1+1, i2);
            long questionId = Long.parseLong(r1.trim());
            Question questions = questionDAO.findById(questionId);
            System.out.println(question + ", " + questionId);
            String user = request.getParameter("users");
            int i3 = user.indexOf("=");
            int i4 = user.indexOf(",");
            String r2 = user.substring(i3+1, i4);
            long userId = Long.parseLong(r2.trim());
            User users = userDAO.findById(userId);
            System.out.println(user + ", " + userId);
            choiceDAO.insert(new Choices(questionId, userId, request.getParameter("userStatus"), questions, users));
        }catch (Exception e){
            System.out.println(e);
        }
        doGet(request,response);
    }
}