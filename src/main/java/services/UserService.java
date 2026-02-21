package services;


import dao.UserDao;
import models.User;
import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao=userDao;
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public Integer saveUser(User user) {
        return userDao.saveUser(user);

    }

    public boolean deleteUserById(int userId) {
       return userDao.deleteUserById(userId);
    }

    public boolean updateUser(User user) {
       return userDao.updateUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

}
