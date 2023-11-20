package com.example.grishaee.Servlets.PostServlets;

import java.io.*;
import java.util.List;

import com.example.grishaee.DAO.Connection.ConnectionProperty;
import com.example.grishaee.DAO.VoteDAO;
import com.example.grishaee.Models.Vote;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/votes")
public class VoteServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final VoteDAO dao;

    public VoteServlet() throws FileNotFoundException, IOException{
        super();
        prop = new ConnectionProperty();
        dao = new VoteDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vote> votes;
        try{
            votes = dao.findAll();
            request.setAttribute("votes", votes);
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/votes.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            dao.insert(new Vote(request.getParameter("title"), request.getParameter("dateStart"),
                    request.getParameter("dateFinish"), request.getParameter("status")));
        }catch (Exception e){
            System.out.println(e);
        }

        doGet(request, response);
    }
}