package services;


import dao.UserDao;
import models.User;
import java.util.List;

public class UserService {

    private final UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User getUserById(int id) {
        return usersDao.getUserById(id);
    }

    public Integer saveUser(User user) {
        return usersDao.saveUser(user);

    }

    public boolean deleteUserById(int userId) {
       return usersDao.deleteUserById(userId);
    }

    public boolean updateUser(User user) {
       return usersDao.updateUser(user);
    }

    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }

}
