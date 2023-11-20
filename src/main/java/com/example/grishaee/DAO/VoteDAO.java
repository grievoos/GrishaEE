package com.example.grishaee.DAO;

import com.example.grishaee.DAO.Connection.ConnectionBuilder;
import com.example.grishaee.DAO.Connection.DbConnectionBuilder;
import com.example.grishaee.Models.Vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
public class VoteDAO implements RepositoryDAO<Vote> {
    public VoteDAO(){};
    private static final String select_all = "SELECT vote_id, title, date_start, date_finish, status FROM vote";
    private static final String select_vote_ById = "SELECT vote_id, title, date_start, date_finish, status FROM vote WHERE vote_id =?";
    private static final String insert_vote = "INSERT INTO vote(title, date_start, date_finish, status) VALUES(?, ?, ?, ?)";
    private static final String edit_vote = "UPDATE vote SET title = ?, date_start = ?, date_finish = ?, status = ? WHERE vote_id = ? ";
    private static final String delete_vote = "DELETE FROM vote WHERE vote_id = ?";
    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws Exception{
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (Vote votes) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_vote, new String[] { "vote_id" })) {
            long Id = -1L;
            pst.setString(1, votes.getTitle());
            pst.setString(2, votes.getDateStart());
            pst.setString(3, votes.getDateFinish());
            pst.setString(4, votes.getStatus());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("vote_id");
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
    public void update(Vote votes) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit_vote)) {
            pst.setString(1, votes.getTitle());
            pst.setString(2, votes.getDateStart());
            pst.setString(3, votes.getDateFinish());
            pst.setString(4, votes.getStatus());
            pst.setLong(5, votes.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Удаление должности
    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete_vote)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Поиск должности по Id
    @Override
    public Vote findById(Long Id) {
        Vote votes = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_vote_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                votes = fillvotes(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return votes;
    }
    // Формирование списка всех должностей
    @Override
    public List<Vote> findAll(){
        List<Vote> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillvotes(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private Vote fillvotes(ResultSet rs) throws SQLException {
        Vote votes = new Vote();
        votes.setId(rs.getLong("vote_id"));
        votes.setTitle(rs.getString("title"));
        votes.setDateStart(rs.getString("date_start"));
        votes.setDateFinish(rs.getString("date_finish"));
        votes.setStatus(rs.getString("status"));
        return votes;
    }
    // Поиск должности по id из списка должностей
    public Vote FindById(Long id, List<Vote> votes) {
        if (votes != null) {
            for (Vote r : votes) {
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
