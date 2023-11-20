package com.example.grishaee.DAO;

import com.example.grishaee.DAO.Connection.ConnectionBuilder;
import com.example.grishaee.DAO.Connection.DbConnectionBuilder;
import com.example.grishaee.Models.Choices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ChoiceDAO implements RepositoryDAO<Choices> {
    public ChoiceDAO(){}

    private static final String select_all = "SELECT choice_id, question_id, user_id, user_choice FROM choice";
    private static final String select_choice_ById = "SELECT choice_id, question_id, user_id, user_choice FROM choice WHERE choice_id = ?";
    private static final String insert_choice = "INSERT INTO choice(question_id, user_id, user_choice) VALUES(?, ?, ?)";
    private static final String edit_choice = "UPDATE choice SET question_id = ?, user_id = ?, user_choice = ? WHERE choice_id = ? ";
    private static final String delete_choice = "DELETE FROM choice WHERE choice_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (Choices choice) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_choice, new String[] { "choice_id" })) {
            long Id = -1L;
            pst.setLong(1, choice.getQuestionId());
            pst.setLong(2, choice.getUserId());
            pst.setString(3, choice.getUserChoice());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("choice_id");
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
    public void update(Choices choice) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit_choice)) {
            pst.setLong(1, choice.getQuestionId());
            pst.setLong(2, choice.getUserId());
            pst.setString(3, choice.getUserChoice());
            pst.setLong(4, choice.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Удаление должности
    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete_choice)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Поиск должности по Id
    @Override
    public Choices findById(Long Id) {
        Choices choice = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_choice_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                choice = fillchoices(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return choice;
    }
    // Формирование списка всех должностей
    @Override
    public List<Choices> findAll(){
        List<Choices> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillchoices(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private Choices fillchoices(ResultSet rs) throws SQLException {
        Choices choice = new Choices();
        choice.setId(rs.getLong("choice_id"));
        choice.setQuestionId(rs.getLong("question_id"));
        choice.setUserId(rs.getLong("user_id"));
        choice.setUserChoice(rs.getString("user_choice"));
        return choice;
    }
    // Поиск должности по id из списка должностей
    public Choices FindById(Long id, List<Choices> choice) {
        if (choice != null) {
            for (Choices r : choice) {
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