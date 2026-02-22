import dao.UserDao;
import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.sql.Date;
import java.util.List;

@Testcontainers
public class UserDaoTest {

    @Container
    private static final PostgreSQLContainer<?>
            postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("mydb")
            .withUsername("postgres")
            .withPassword("12345");

    private static SessionFactory sessionFactory;
    private UserDao userDao;

    @BeforeAll
    static void setup() {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        cfg.setProperty("hibernate.connection.username", postgres.getUsername());
        cfg.setProperty("hibernate.connection.password", postgres.getPassword());
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        cfg.addAnnotatedClass(models.User.class);
        sessionFactory = cfg.buildSessionFactory();
    }

    @BeforeEach
    void init() {
        userDao = new UserDao();
    }

    @AfterAll
    static void fin() {
        if (sessionFactory != null) sessionFactory.close();
    }

    @Test
     void testSaveUser() {
        User testUser = new User ("Vasya","vasya@mail.ru",23,new Date(2025,12,11));
        Integer id=userDao.saveUser(testUser);
        User foundUser = userDao.getUserById(id);
        Assertions.assertEquals("Vasya", foundUser.getName());

    }

    @Test
    void testGetUserById() {
        User testUser = new User ("Vasya","vasya@mail.ru",23,new Date(2025,12,11));
        Integer id=userDao.saveUser(testUser);
        User foundUser = userDao.getUserById(id);
        Assertions.assertEquals("Vasya", foundUser.getName());

    }

    @Test
    void testUpdateUser() {
        User testUser = new User ("Vasya","vasya@mail.ru",23,new Date(2025,12,11));
        boolean flag =userDao.updateUser(testUser);
        Assertions.assertTrue(flag);

    }

    @Test
    void testDeleteUserById() {
        User testUser = new User ("Vasya","vasya@mail.ru",23,new Date(2025,12,11));
        Integer testId=userDao.saveUser(testUser);
        boolean flag =userDao.deleteUserById(testId);
        Assertions.assertTrue(flag);

    }

    @Test
    void testGetAllUsers() {
        User testUser1 = new User ("Vasya","vasya@mail.ru",23,new Date(2025,12,11));
        User testUser2 = new User ("Vanya","vanya@mail.ru",22,new Date(2025,12,11));
        User testUser3 = new User ("Valya","valya@mail.ru",21,new Date(2025,12,11));
        Integer testId1= userDao.saveUser(testUser1);
        Integer testId2= userDao.saveUser(testUser2);
        Integer testId3=userDao.saveUser(testUser3);
        List<User> testUserList=userDao.getAllUsers();
        Assertions.assertNotNull(testUserList);
        Assertions.assertFalse(testUserList.isEmpty());
    }
 }
