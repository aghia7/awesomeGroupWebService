package repositories;

import data.interfaces.DBConnection;
import entities.User;
import repositories.interfaces.IUserRepository;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ServerErrorException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUserRepository {
    @Inject
    private DBConnection db;


    @Override
    public boolean addUser(User user) {
        Connection con = db.getConnection();
        try {
            String sql = "INSERT INTO users(name, surname, username, gender) " +
                    "VALUES (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getUsername());
            stmt.setBoolean(4, user.getGender());
            stmt.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        Connection con = db.getConnection();
        try {
            String sql = "SELECT id, name, surname, gender FROM users WHERE username = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        username,
                        rs.getBoolean("gender")
                );

                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public User getUser(int id) {
        Connection con = db.getConnection();
        try {
            String sql = "SELECT id, name, surname, username, gender " +
                    "FROM users WHERE id = " + id;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getBoolean("gender")
                );

                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        Connection con = db.getConnection();
        try {
            String sql = "SELECT id, name, username, surname, gender FROM users ORDER BY id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<User> users = new LinkedList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getBoolean("gender")
                );

                users.add(user);
            }

            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new BadRequestException("Request has mistakes");
        }
    }
}
