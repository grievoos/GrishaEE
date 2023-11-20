package com.example.grishaee.DAO;

import com.example.grishaee.DAO.Connection.ConnectionBuilder;
import com.example.grishaee.DAO.Connection.DbConnectionBuilder;
import com.example.grishaee.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
public class UserDAO implements RepositoryDAO<User> {
    public UserDAO(){};

    private static final String select_all = "SELECT user_id, second_name, first_name, last_name, email, phone, user_status FROM users";
    private static final String select_user_ById = "SELECT user_id, second_name, first_name, last_name, email, phone, user_status FROM users WHERE user_id =?";
    private static final String insert_user = "INSERT INTO users(second_name, first_name, last_name, email, phone, user_status) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String edit_user = "UPDATE users SET second_name = ?, first_name = ?, last_name = ?, email = ?, phone = ?, user_status = ? WHERE user_id = ? ";
    private static final String delete_user = "DELETE FROM users WHERE user_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws Exception{
        return builder.getConnection();
    }

    // Добавление новой должности
    @Override
    public Long insert (User users) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_user, new String[] { "user_id" })) {
            long Id = -1L;
            pst.setString(1, users.getSecondName());
            pst.setString(2, users.getFirstName());
            pst.setString(3, users.getLastName());
            pst.setString(4, users.getEmail());
            pst.setString(5, users.getPhone());
            pst.setString(6, users.getUserStatus());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("user_id");
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
    public void update(User users) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit_user)) {
            pst.setString(1, users.getSecondName());
            pst.setString(2, users.getFirstName());
            pst.setString(3, users.getLastName());
            pst.setString(4, users.getEmail());
            pst.setString(5, users.getPhone());
            pst.setString(6, users.getUserStatus());
            pst.setLong(7, users.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Удаление должности
    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete_user)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Поиск должности по Id
    @Override
    public User findById(Long Id) {
        User users = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_user_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                users = filluser(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return users;
    }
    // Формирование списка всех должностей
    @Override
    public List<User> findAll(){
        List<User> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(filluser(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private User filluser(ResultSet rs) throws SQLException {
        User users = new User();
        users.setId(rs.getLong("user_id"));
        users.setSecondName(rs.getString("second_name"));
        users.setFirstName(rs.getString("first_name"));
        users.setLastName(rs.getString("last_name"));
        users.setEmail(rs.getString("email"));
        users.setPhone(rs.getString("phone"));
        users.setUserStatus(rs.getString("user_status"));
        return users;
    }
    // Поиск должности по id из списка должностей
    public User FindById(Long id, List<User> users) {
        if (users != null) {
            for (User r : users) {
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
