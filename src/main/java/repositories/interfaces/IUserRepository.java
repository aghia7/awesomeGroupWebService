package repositories.interfaces;

import entities.User;

import java.util.List;

public interface IUserRepository {
    boolean addUser(User user);
    User getUserByUsername(String username);
    boolean deleteUser(int id);
    User getUser(int id);
    List<User> getAll();
}
