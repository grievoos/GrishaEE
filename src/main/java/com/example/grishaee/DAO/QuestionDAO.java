package com.example.grishaee.DAO;

import com.example.grishaee.DAO.Connection.ConnectionBuilder;
import com.example.grishaee.DAO.Connection.DbConnectionBuilder;
import com.example.grishaee.Models.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class QuestionDAO implements RepositoryDAO<Question> {
    public QuestionDAO(){}

    private static final String select_all = "SELECT question_id, vote_id, content, date_vote FROM question";
    private static final String select_question_ById = "SELECT question_id, vote_id, content, date_vote FROM question WHERE question_id = ?";
    private static final String insert_question = "INSERT INTO question(vote_id, content, date_vote) VALUES(?, ?, ?)";
    private static final String edit_question = "UPDATE question SET vote_id = ?, content = ?, date_vote = ? WHERE question_id = ? ";
    private static final String delete_question = "DELETE FROM question WHERE question_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (Question question) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_question, new String[] { "question_id" })) {
            long Id = -1L;
            pst.setLong(1, question.getVoteId());
            pst.setString(2, question.getContent());
            pst.setString(3, question.getDateVote());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("question_id");
            }
            gk.close();
            return Id;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1L;
    }
    // Редактирование должности
    @Override
    public void update(Question question) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit_question)) {
            pst.setLong(1, question.getVoteId());
            pst.setString(2, question.getContent());
            pst.setString(3, question.getDateVote());
            pst.setLong(4, question.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Удаление должности
    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete_question)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Поиск должности по Id
    @Override
    public Question findById(Long Id) {
        Question question = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_question_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                question = fillquestion(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return question;
    }
    // Формирование списка всех должностей
    @Override
    public List<Question> findAll(){
        List<Question> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillquestion(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private Question fillquestion(ResultSet rs) throws SQLException {
        Question question = new Question();
        question.setId(rs.getLong("question_id"));
        question.setVoteId(rs.getLong("vote_id"));
        question.setContent(rs.getString("content"));
        question.setDateVote(rs.getString("date_vote"));
        return question;
    }
    // Поиск должности по id из списка должностей
    public Question FindById(Long id, List<Question> question) {
        if (question != null) {
            for (Question r : question) {
                if ((r.getId()).equals(id)) {
                    return r;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}